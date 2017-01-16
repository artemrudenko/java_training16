package by.stqa.pft.mantis.appmanager;

import by.stqa.pft.mantis.model.UserData;
import by.stqa.pft.mantis.model.Users;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

/**
 * Created by artemr on 1/16/2017.
 */
public class UserHelper extends HelperBase{

  public UserHelper(ApplicationManager app){
    super(app);
  }
  public Users all() {
    if (userCache != null){
      return new Users(userCache);
    }
    userCache = new Users();
    List<WebElement> rows = wd.findElements(By.xpath("//tr[@class=\"row-1\" or @class=\"row-2\"]"));
    for(WebElement row: rows){
      String href = row.findElement(By.cssSelector("td:nth-child(1)")).getAttribute("href");
      int id = Integer.parseInt(href.split("=")[1]);
      String username = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
      String email = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      userCache.add(new UserData()
              .withId(id)
              .withUsername(username)
              .withEmail(email));
    }
    return new Users(userCache);
  }

  public int count() {
    return wd.findElements(By.xpath("//tr[@class=\"row-1\" or @class=\"row-2\"]")).size();
  }

  private Users userCache = null;

  public void initCreation() {
    click(By.cssSelector("input[value='Create New Account']"));
    wait.until(ExpectedConditions.urlContains("manage_user_create_page.php"));
  }

  public void submitCreation() {
    click(By.cssSelector("input[value='Create User']"));
    wait.until(ExpectedConditions.urlContains("manage_user_edit_page.php"));
    userCache = null;
  }

  public void fillForm(UserData userData) {
    type(By.name("username"), userData.getUsername());
//    type(By.name("realname"), userData.getRealname());
    type(By.name("email"), userData.getEmail());
//    select(By.name("access_level"), contactData.getAccessLevel());
  }

  public void create(UserData data){
    initCreation();
    fillForm(data);
    submitCreation();
  }

  public void resetUserPassword(UserData userData){
    initEditUserData(userData);
    click(By.cssSelector("input[value='Reset Password']"));
    wait.until(ExpectedConditions.urlContains("manage_user_page.php"));
  }

  public void initEditUserData(UserData userData){
    String locator = String.format("//a[contains(@href, 'user_id=%s')]", userData.getId());
    click(By.xpath(locator));
    wait.until(ExpectedConditions.urlContains(String.format("manage_user_edit_page.php?user_id=%s", userData.getId())));
  }
}
