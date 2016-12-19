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

  @Test(enabled = false)
  public void testContactModification() {
    app.goTo().gotoToHomePage();
    if (!app.contact().isThereAContact()) {
      ContactData data = app.contact().generate(null);
      app.contact().createContact(data);
      app.goTo().gotoToHomePage();
    }
    List<ContactData> before = app.contact().getGontactsList();
    app.contact().initContactModificationById(before.size() - 1);
    ContactData data = app.contact().generate();
    data.setId(before.get(before.size() - 1).getId());
    app.contact().fillContactForm(data, false);
    app.contact().submitContactUpdate();

    app.goTo().gotoToHomePage();
    List<ContactData> after = app.contact().getGontactsList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(data);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
