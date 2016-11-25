package by.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by artemr on 11/25/2016.
 */
public class ContactDeletionTest extends TestBase{
  @Test
  public void testNotConfirmContactDeletion(){
    String[] contacts = app.getContactHelper().getContacts();
    int before = contacts.length;
    app.getContactHelper().selectContact(contacts[0]);
    app.getContactHelper().deleteSelectedContacts(false);
    Assert.assertEquals(app.getContactHelper().getContacts().length, before,
            "The same amount of contacts should present");
  }
  @Test
  public void testConfirmSingleContactDeletion(){
    String[] contacts = app.getContactHelper().getContacts();
    int before = contacts.length;
    app.getContactHelper().selectContact(contacts[0]);
    app.getContactHelper().deleteSelectedContacts(true);
    Assert.assertEquals(app.getContactHelper().getContacts().length, before-1,
            "One and only one item deleted if single item selected");

  }
}
