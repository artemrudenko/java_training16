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

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getGontactsList();
    ContactData data = app.getContactHelper().generate();
    app.getContactHelper().createContact(data);
    app.getNavigationHelper().gotoToHomePage();
    List<ContactData> after = app.getContactHelper().getGontactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(data);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
