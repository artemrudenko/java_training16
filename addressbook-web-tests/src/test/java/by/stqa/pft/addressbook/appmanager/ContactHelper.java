package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.ContactData;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.codearte.jfairy.producer.person.PersonProperties.withCompany;

/**
 * Created by artemr on 11/25/2016.
 */
public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
    waitHomePageLoad();
  }

  public void submitContactUpdate() {
    click(By.name("update"));
    waitHomePageLoad();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
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

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(String text){
    clickTableElement(By.id("maintable"), text, By.name("selected[]"));
  }

  public void selectContactById(int index){
    wd.findElement(By.id("maintable")).findElements(By.name("selected[]")).get(index).click();
  }

  public String[] getContacts(){
    return getTableRowsText(By.id("maintable"));
  }

  public void initContactModification(String text){
    clickTableElement(By.id("maintable"), text, By.cssSelector("a img[title='Edit']"));
  }

  public void initContactModificationById(int index){
    wd.findElement(By.id("maintable")).findElements(By.cssSelector("a img[title='Edit']")).get(index).click();
  }

  public void deleteSelectedContacts(boolean confirm){
    click(By.cssSelector("input[value='Delete']"));
    closeAlert(confirm);
    waitHomePageLoad();
  }

  private void waitHomePageLoad() {
    waitElementVisible(By.id("maintable"));
  }

  public ContactData generate(){
    Fairy fairy = Fairy.create();
    Company company = fairy.company();
    Person person = fairy.person(withCompany(company));
    return new ContactData(person.getFirstName(), person.getLastName(), person.getAddress().getStreet());
  }

  public ContactData generate(String group){
    Fairy fairy = Fairy.create();
    Company company = fairy.company();
    Person person = fairy.person(withCompany(company));
    Address address = person.getAddress();
    return new ContactData(person.getFirstName(), person.getMiddleName(), person.getLastName(),
            person.getFullName(), (person.getSex() == Person.Sex.MALE) ? "Mr." : "Ms.", person.getCompany().getName(),
            address.getStreet(), address.getAddressLine1(), person.getTelephoneNumber(), person.getTelephoneNumber(),
            person.getTelephoneNumber(), person.getEmail(), person.getCompanyEmail(), person.getEmail(),
            person.getEmail(), "2-May-2001", "10-September-2021", group,
            person.getAddress().getAddressLine2(), person.getTelephoneNumber(),
            "Some notes about: " + person.getNationalIdentificationNumber());
  }

  public boolean isThereAContact(){
    return isElementPresent(By.cssSelector("#maintable [name='selected[]']"));
  }

  public void createContact(ContactData data){
    initContactCreation();
    fillContactForm(data, true);
    submitContactCreation();
  }

  public List<ContactData> getGontactsList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name=entry]"));
    for(WebElement row: rows){
      int id = Integer.parseInt(row.findElement(By.cssSelector("input[name='selected[]']")).getAttribute("value"));
      String lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstname = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String address = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
      contacts.add(new ContactData(id, firstname, lastname, address));
    }
    return contacts;


  }
}
