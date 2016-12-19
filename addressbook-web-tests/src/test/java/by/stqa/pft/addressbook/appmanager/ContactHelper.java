package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static io.codearte.jfairy.producer.person.PersonProperties.withCompany;

/**
 * Created by artemr on 11/25/2016.
 */
public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name=entry]"));
    for(WebElement row: rows){
      int id = Integer.parseInt(row.findElement(By.cssSelector("input[name='selected[]']")).getAttribute("value"));
      String lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstname = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String address = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String[] emails = row.findElement(By.cssSelector("td:nth-child(5)")).getText().split("\n");
      String[] phones = row.findElement(By.cssSelector("td:nth-child(6)")).getText().split("\n");
      contactCache.add(new ContactData()
              .withId(id)
              .withFirstname(firstname)
              .withLastname(lastname)
              .withAddress(address)
              .withHomePhone(phones[0])
              .withMobilePhone(phones[1])
              .withWorkPhone(phones[2]));
    }
    return new Contacts(contactCache);
  }

  public int count() {
    return wd.findElements(By.cssSelector("tr[name=entry]")).size();
  }

  private Contacts contactCache = null;

  public ContactData generate(){
    Fairy fairy = Fairy.create();
    Company company = fairy.company();
    Person person = fairy.person(withCompany(company));
    return new ContactData()
            .withFirstname(person.getFirstName())
            .withLastname(person.getLastName())
            .withAddress(person.getAddress().getStreet());
  }

  public ContactData generate(String group){
    Fairy fairy = Fairy.create();
    Company company = fairy.company();
    Person person = fairy.person(withCompany(company));
    Address address = person.getAddress();
    return new ContactData().withFirstname(person.getFirstName())
            .withMiddlename(person.getMiddleName())
            .withLastname(person.getLastName())
            .withNickname(person.getFullName())
            .withTitle((person.getSex() == Person.Sex.MALE) ? "Mr." : "Ms.")
            .withCompany(person.getCompany().getName())
            .withAddress(address.getStreet())
            .withHomePhone(person.getTelephoneNumber())
            .withWorkPhone(fairy.person().getTelephoneNumber())
            .withMobilePhone(fairy.person().getTelephoneNumber())
            .withEmail(person.getEmail())
            .withHomepage(person.getEmail())
            .withBirthday("2-May-2001")
            .withAnniversary("10-September-2021")
            .withGroup(group)
            .withAddress2(person.getAddress().getAddressLine2())
            .withNotes("Some notes about: " + person.getNationalIdentificationNumber());
  }

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());

    if(contactData.getBirthday() != null){
      String[] birtday = contactData.getBirthday().split("-");
      select(By.name("bday"), birtday[0]);
      select(By.name("bmonth"), birtday[1]);
      type(By.name("byear"), birtday[2]);
    }

    if(contactData.getAnniversary() != null) {
      String[] anniversary = contactData.getAnniversary().split("-");
      select(By.name("aday"), anniversary[0]);
      select(By.name("amonth"), anniversary[1]);
      type(By.name("ayear"), anniversary[2]);
    }
    if(creation){
      select(By.name("new_group"), contactData.getGroup());
    }else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());
  }

  public void selectById(int id){
    wd.findElement(By.cssSelector("input[value='"+ id + "'")).click();
  }

  public void initCreation() {
    click(By.linkText("add new"));
  }

  public void submitCreation() {
    click(By.name("submit"));
    waitHomePageLoad();
    contactCache = null;
  }

  public void submitUpdate() {
    click(By.name("update"));
    waitHomePageLoad();
    contactCache = null;
  }

  public void initModificationById(int id){
    wd.findElement(By.xpath(String.format("//img[@title='Edit' and ancestor::tr[.//input[@id='%s']]]", id))).click();
  }

  private void waitHomePageLoad() {
    waitElementVisible(By.id("maintable"));
  }

  public boolean isThereAContact(){
    return isElementPresent(By.cssSelector("#maintable [name='selected[]']"));
  }

  public void create(ContactData data){
    initCreation();
    fillForm(data, true);
    submitCreation();
  }

  public void update(ContactData data){
    initModificationById(data.getId());
    fillForm(data, false);
    submitUpdate();
  }

  public void delete(ContactData toRemove, boolean confirm) {
    selectById(toRemove.getId());
    deleteSelected(confirm);
  }

  public void deleteSelected(boolean confirm){
    click(By.cssSelector("input[value='Delete']"));
    closeAlert(confirm);
    waitHomePageLoad();
    contactCache = null;
  }

  public ContactHelper deleteAll(){
    wd.findElement(By.id("MassCB")).click();
    contactCache = null;
    return this;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initModificationById(contact.getId());
    return new ContactData()
            .withId(contact.getId())
            .withLastname(wd.findElement(By.name("lastname")).getAttribute("value"))
            .withFirstname(wd.findElement(By.name("firstname")).getAttribute("value"))
            .withHomePhone(wd.findElement(By.name("home")).getAttribute("value"))
            .withMobilePhone(wd.findElement(By.name("mobile")).getAttribute("value"))
            .withWorkPhone(wd.findElement(By.name("work")).getAttribute("value"));
  }
}
