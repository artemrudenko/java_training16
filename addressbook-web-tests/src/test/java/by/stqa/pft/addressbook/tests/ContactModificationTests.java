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
    if(app.contact().all().size() == 0){
      app.contact().create(app.contact().generate(null));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData toUpdate = before.iterator().next();
    ContactData data = app.contact().generate().withId(toUpdate.getId());
    app.contact().update(data);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withModified(data)));
  }
}
