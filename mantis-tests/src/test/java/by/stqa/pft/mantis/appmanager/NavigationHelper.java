package by.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by artemr on 11/25/2016.
 */
public class NavigationHelper extends HelperBase{

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void managePage() {
    click(By.linkText("Manage"));
    wait.until(ExpectedConditions.urlContains("manage_overview_page.php"));
  }

  public void manageUsersPage(){
    managePage();
    click(By.linkText("Manage Users"));
    wait.until(ExpectedConditions.urlContains("manage_user_page.php"));
  }
}
