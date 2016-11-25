package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by artemr on 11/25/2016.
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }
  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }
  public void returnToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }
}
