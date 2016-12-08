/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoToHomePage();
    if(!app.getContactHelper().isThereAContact()){
      ContactData data = app.getContactHelper().generate(null);
      app.getContactHelper().createContact(data);
      app.getNavigationHelper().gotoToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getGontactsList();
    app.getContactHelper().initContactModificationById(before.size() - 1);
    ContactData data = app.getContactHelper().generate();
    data.setId(before.get(before.size() - 1).getId());
    app.getContactHelper().fillContactForm(data, false);
    app.getContactHelper().submitContactUpdate();

    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> after = app.getContactHelper().getGontactsList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(data);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
