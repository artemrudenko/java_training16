/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    ContactData data = app.getContactHelper().generate(null);
    app.getContactHelper().createContact(data);
    app.getNavigationHelper().gotoToHomePage();
  }

}
