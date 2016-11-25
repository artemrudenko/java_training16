/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook;

import org.testng.annotations.Test;
import org.openqa.selenium.*;


public class GroupCreationTests extends BaseAddressbookTest{
  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("MyGroup1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
  }

  private void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  private void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  private void fillGroupForm(GroupData groupData) {
    setInputValue(By.name("group_name"), groupData.getName());
    setInputValue(By.name("group_header"), groupData.getHeader());
    setInputValue(By.name("group_footer"), groupData.getFooter());
  }

  private void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  private void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

}
