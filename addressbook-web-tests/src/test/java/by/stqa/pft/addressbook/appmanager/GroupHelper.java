package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemr on 11/25/2016.
 */
public class GroupHelper extends HelperBase{
  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initCreation() {
    click(By.name("new"));
  }

  public void deleteSelected() {
    click(By.name("delete"));
  }

  public void select(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initModification() {
    click(By.name("edit"));
  }

  public void submitModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initCreation();
    fillGroupForm(group);
    submitCreation();
    returnToGroupPage();
  }

  public boolean isExists(String name){
    return isElementPresent(By.xpath("//span[contains(.,'" + name + "')]"));
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for(WebElement el: elements){
      int id = Integer.parseInt(el.findElement(By.tagName("input")).getAttribute("value"));
      String name = el.getText();
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }

  public void modify(int index, GroupData group) {
    select(index);
    initModification();
    fillGroupForm(group);
    submitModification();
    returnToGroupPage();
  }

  public void delete(int index) {
    select(index);
    deleteSelected();
    returnToGroupPage();
  }

}
