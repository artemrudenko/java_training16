/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;
import static io.codearte.jfairy.producer.person.PersonProperties.withCompany;
import org.testng.annotations.Test;
import org.openqa.selenium.*;


public class ContactCreationTests extends BaseAddressbookTest {

  @Test
  public void testContactCreation() {
    Fairy fairy = Fairy.create();
    Company company = fairy.company();
    Person person = fairy.person(withCompany(company));
    initContactCreation();
    Address address = person.getAddress();
    System.out.println("Middle name is" + person.getMiddleName());
    fillContactForm(new ContactData(person.getFirstName(), person.getMiddleName(), person.getLastName(),
            person.getFullName(), (person.getSex() == Person.Sex.MALE)? "Mr.": "Ms.", person.getCompany().getName(),
            address.getStreet(), address.getAddressLine1(), person.getTelephoneNumber(), person.getTelephoneNumber(),
            person.getTelephoneNumber(), person.getEmail(), person.getCompanyEmail(), person.getEmail(),
            person.getEmail(), "2-May-2001", "10-September-2021", "MyGroup1",
            person.getAddress().getAddressLine2(), person.getTelephoneNumber(),
            "Some notes about: " + person.getNationalIdentificationNumber()));
    submitContactCreation();
    returnToHomePage();
  }

  private void returnToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  private void submitContactCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void fillContactForm(ContactData contactData) {
    setInputValue(By.name("firstname"), contactData.getFirstname());
    setInputValue(By.name("middlename"), contactData.getMiddlename());
    setInputValue(By.name("lastname"), contactData.getLastname());
    setInputValue(By.name("nickname"), contactData.getNickname());
    setInputValue(By.name("title"), contactData.getTitle());
    setInputValue(By.name("company"), contactData.getCompany());
    setInputValue(By.name("address"), contactData.getAddress());
    setInputValue(By.name("home"), contactData.getHome());
    setInputValue(By.name("mobile"), contactData.getMobile());
    setInputValue(By.name("work"), contactData.getWork());
    setInputValue(By.name("fax"), contactData.getFax());
    setInputValue(By.name("email"), contactData.getEmail());
    setInputValue(By.name("email2"), contactData.getEmail2());
    setInputValue(By.name("email3"), contactData.getEmail3());
    setInputValue(By.name("homepage"), contactData.getHomepage());

    String[] birtday = contactData.getBirthday().split("-");
    selectValue(By.name("bday"), birtday[0]);
    selectValue(By.name("bmonth"), birtday[1]);
    setInputValue(By.name("byear"), birtday[2]);

    String[] anniversary = contactData.getAnniversary().split("-");
    selectValue(By.name("aday"), anniversary[0]);
    selectValue(By.name("amonth"), anniversary[1]);
    setInputValue(By.name("ayear"), anniversary[2]);

    selectValue(By.name("new_group"), contactData.getGroup());
    setInputValue(By.name("address2"), contactData.getAddress2());
    setInputValue(By.name("phone2"), contactData.getPhone2());
    setInputValue(By.name("notes"), contactData.getNotes());
  }

  private void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

}
