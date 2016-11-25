package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by artemr on 11/25/2016.
 */
public class NavigationHelper extends HelperBase{
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }
  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }
  public void returnToHomePage() {
    click(By.linkText("home"));
  }
}
