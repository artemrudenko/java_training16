package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.GroupData;
import by.stqa.pft.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by artemr on 11/25/2016.
 */
public class GroupHelper extends HelperBase{
  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public boolean isThereAGroup(){
    return isElementPresent(By.name("selected[]"));
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

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id + "'")).click();
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

  public Groups all() {
    Groups groups = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for(WebElement el: elements){
      int id = Integer.parseInt(el.findElement(By.tagName("input")).getAttribute("value"));
      String name = el.getText();
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initModification();
    fillGroupForm(group);
    submitModification();
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelected();
    returnToGroupPage();
  }

}
