/**
 * Created by artemr on 11/25/2016.
 */
package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTest extends TestBase{
  @Test(enabled = false)
  public void testNotConfirmContactDeletion(){
    app.goTo().gotoToHomePage();
    if(!app.contact().isThereAContact()){
      ContactData data = app.contact().generate(null);
      app.contact().createContact(data);
      app.goTo().gotoToHomePage();
    }
    List<ContactData> before = app.contact().getGontactsList();
    app.contact().selectContactById(before.size() - 1);
    app.contact().deleteSelectedContacts(false);
    List<ContactData> after = app.contact().getGontactsList();
    Assert.assertEquals(after.size(), before.size());
    Assert.assertEquals(before, after);
  }

  @Test(enabled = false)
  public void testConfirmSingleContactDeletion(){
    app.goTo().gotoToHomePage();
    if(!app.contact().isThereAContact()){
      ContactData data = app.contact().generate(null);
      app.contact().createContact(data);
      app.goTo().gotoToHomePage();
    }
    List<ContactData> before = app.contact().getGontactsList();
    app.contact().selectContactById(before.size() - 1);
    app.contact().deleteSelectedContacts(true);
    List<ContactData> after = app.contact().getGontactsList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size()-1);
    Assert.assertEquals(before, after);
  }
}
