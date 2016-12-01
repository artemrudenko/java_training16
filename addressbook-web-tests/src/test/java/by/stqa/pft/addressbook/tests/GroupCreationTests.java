/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {
  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("MyGroup1", null, null));
  }

}
