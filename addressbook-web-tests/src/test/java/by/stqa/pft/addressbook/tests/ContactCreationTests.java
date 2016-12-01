/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    ContactData data = app.getContactHelper().generate("MyGroup1");
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(data, true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }

}
