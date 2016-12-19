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
    List<ContactData> before = app.contact().getGontactsList();
    ContactData data = app.contact().generate();
    app.contact().createContact(data);
    app.goTo().gotoToHomePage();
    List<ContactData> after = app.contact().getGontactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(data);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test(enabled = false)
  public void testFullContactCreation() {
    List<ContactData> before = app.contact().getGontactsList();
    ContactData data = app.contact().generate(null);
    app.contact().createContact(data);
    app.goTo().gotoToHomePage();
    List<ContactData> after = app.contact().getGontactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    data.setId(after.stream().max(byId).get().getId());
    before.add(data);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
