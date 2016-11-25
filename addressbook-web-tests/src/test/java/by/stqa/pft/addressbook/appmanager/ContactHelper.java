package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.ContactData;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static io.codearte.jfairy.producer.person.PersonProperties.withCompany;

/**
 * Created by artemr on 11/25/2016.
 */
public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean edit) {
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

    String[] birtday = contactData.getBirthday().split("-");
    select(By.name("bday"), birtday[0]);
    select(By.name("bmonth"), birtday[1]);
    type(By.name("byear"), birtday[2]);

    String[] anniversary = contactData.getAnniversary().split("-");
    select(By.name("aday"), anniversary[0]);
    select(By.name("amonth"), anniversary[1]);
    type(By.name("ayear"), anniversary[2]);
    if(!edit){
      select(By.name("new_group"), contactData.getGroup());
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

  public String[] getContacts(){
    return getTableRowsText(By.id("maintable"));
  }

  public void initContactModification(String text){
    clickTableElement(By.id("maintable"), text, By.cssSelector("a img[title='Edit']"));
  }

  public void deleteSelectedContacts(boolean confirm){
    click(By.cssSelector("input[value='Delete']"));
    closeAlert(confirm);
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
}
