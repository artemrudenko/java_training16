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
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name=entry]"));
    for(WebElement row: rows){
      int id = Integer.parseInt(row.findElement(By.cssSelector("input[name='selected[]']")).getAttribute("value"));
      String lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstname = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String address = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address));
    }
    return contacts;
  }

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
            .withMobile(person.getTelephoneNumber())
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
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
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
  }

  public void submitUpdate() {
    click(By.name("update"));
    waitHomePageLoad();
  }

  public void initModificationById(int id){
    wd.findElement(By.xpath("//img[@title='Edit' and ancestor::tr[.//input[@id='" + id + "']]]")).click();
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
  }

  public ContactHelper deleteAll(){
    wd.findElement(By.id("MassCB")).click();
    return this;
  }

}
