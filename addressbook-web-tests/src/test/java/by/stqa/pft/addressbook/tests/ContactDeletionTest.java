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


public class ContactDeletionTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if(app.contact().all().size() == 0){
      app.contact().create(app.contact().generate(null));
    }
  }

  @Test
  public void testNotConfirmContactDeletion(){
    Contacts before = app.contact().all();
    ContactData toRemove = before.iterator().next();
    app.contact().delete(toRemove, false);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
  }

  @Test
  public void testConfirmSingleContactDeletion(){
    Contacts before = app.contact().all();
    ContactData toRemove = before.iterator().next();
    app.contact().delete(toRemove, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(toRemove)));
  }

}
