/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getGontactsList();
    ContactData data = app.getContactHelper().generate();
    app.getContactHelper().createContact(data);
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> after = app.getContactHelper().getGontactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(data);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test(enabled = false)
  public void testFullContactCreation() {
    List<ContactData> before = app.getContactHelper().getGontactsList();
    ContactData data = app.getContactHelper().generate(null);
    app.getContactHelper().createContact(data);
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> after = app.getContactHelper().getGontactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    data.setId(after.stream().max(byId).get().getId());
    before.add(data);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
