package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by artemr on 11/25/2016.
 */
public class GroupHelper {
  private FirefoxDriver wd;
  private CommonHelper commonHelper;

  public GroupHelper(FirefoxDriver wd, CommonHelper commonHelper) {
    this.wd = wd;
    this.commonHelper = commonHelper;
  }

  public void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  public void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  public void fillGroupForm(GroupData groupData) {
    commonHelper.setInputValue(By.name("group_name"), groupData.getName());
    commonHelper.setInputValue(By.name("group_header"), groupData.getHeader());
    commonHelper.setInputValue(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  public void deleteSelectedGroups() {
    wd.findElement(By.name("delete")).click();
  }

  public void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }
}
