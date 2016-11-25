/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;

import static io.codearte.jfairy.producer.person.PersonProperties.withCompany;

import org.testng.annotations.Test;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Fairy fairy = Fairy.create();
    Company company = fairy.company();
    Person person = fairy.person(withCompany(company));
    app.getContactHelper().initContactCreation();
    Address address = person.getAddress();
    System.out.println("Middle name is" + person.getMiddleName());
    app.getContactHelper().fillContactForm(new ContactData(person.getFirstName(), person.getMiddleName(), person.getLastName(),
            person.getFullName(), (person.getSex() == Person.Sex.MALE) ? "Mr." : "Ms.", person.getCompany().getName(),
            address.getStreet(), address.getAddressLine1(), person.getTelephoneNumber(), person.getTelephoneNumber(),
            person.getTelephoneNumber(), person.getEmail(), person.getCompanyEmail(), person.getEmail(),
            person.getEmail(), "2-May-2001", "10-September-2021", "MyGroup1",
            person.getAddress().getAddressLine2(), person.getTelephoneNumber(),
            "Some notes about: " + person.getNationalIdentificationNumber()));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }

}
