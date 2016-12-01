/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    String[] rowsText = app.getContactHelper().getContacts();
    app.getContactHelper().initContactModification(rowsText[0]);
    ContactData data = app.getContactHelper().generate("MyGroup1");
    app.getContactHelper().fillContactForm(data, false);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }
}
