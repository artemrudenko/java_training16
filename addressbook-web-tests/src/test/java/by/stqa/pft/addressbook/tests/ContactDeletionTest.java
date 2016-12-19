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
    app.getNavigationHelper().gotoToHomePage();
    if(!app.getContactHelper().isThereAContact()){
      ContactData data = app.getContactHelper().generate(null);
      app.getContactHelper().createContact(data);
      app.getNavigationHelper().gotoToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getGontactsList();
    app.getContactHelper().selectContactById(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts(false);
    List<ContactData> after = app.getContactHelper().getGontactsList();
    Assert.assertEquals(after.size(), before.size());
    Assert.assertEquals(before, after);
  }

  @Test(enabled = false)
  public void testConfirmSingleContactDeletion(){
    app.getNavigationHelper().gotoToHomePage();
    if(!app.getContactHelper().isThereAContact()){
      ContactData data = app.getContactHelper().generate(null);
      app.getContactHelper().createContact(data);
      app.getNavigationHelper().gotoToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getGontactsList();
    app.getContactHelper().selectContactById(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts(true);
    List<ContactData> after = app.getContactHelper().getGontactsList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size()-1);
    Assert.assertEquals(before, after);
  }
}
