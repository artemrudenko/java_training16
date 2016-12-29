package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.of;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by artemr on 12/20/2016.
 */
public class ContactDetailsTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
//    Is better to uncomment next line once to remove all "broken" records and to add new clear one
    app.contact().deleteAll();
//    if (app.contact().all().size() == 0) {
    app.contact().create(app.contact().generate());
//    }
  }

  @Test
  public void testContactDetails() {
    Contacts before = app.db().contacts();
    ContactData contact = before.iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    app.goTo().homePage();
    assertThat(mergeData(contactInfoFromEditForm), equalTo(app.contact().infoFromDetailsForm(contact)));
  }

  private String mergeData(ContactData contact) {
    String homePhone = contact.getHomePhone();
    String mobilePhone = contact.getMobilePhone();
    String workPhone = contact.getWorkPhone();
    String title = of(contact.getFirstname(), contact.getMiddlename(), contact.getLastname())
            .filter((s) -> !s.equals(""))
            .collect(joining(" "));
    return of(title,
            contact.getNickname(),
            contact.getTitle(),
            contact.getCompany(),
            contact.getAddress(),
            "\n",
            (!homePhone.equals("")) ? "H: " + homePhone : "",
            (!mobilePhone.equals("")) ? "M: " + mobilePhone : "",
            (!workPhone.equals("")) ? "W: " + workPhone : "",
            "\n",
            contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
            (!contact.getHomepage().equals("")) ? "Homepage: " + contact.getHomepage() : "")
            .filter((s) -> !s.equals(""))
            .map((s)-> (s.equals("\n")) ? s.replace("\n", ""): s)
            .collect(joining("\n"));
  }

}
