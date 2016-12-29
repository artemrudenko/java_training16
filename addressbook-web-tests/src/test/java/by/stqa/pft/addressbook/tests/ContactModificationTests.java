/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.contact().create(app.contact().generate());
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData toUpdate = before.iterator().next();
    ContactData data = app.contact().generate().withId(toUpdate.getId());
    app.contact().update(data);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withModified(data)));
  }
}
