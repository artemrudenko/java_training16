/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {
  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("MyGroup1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
