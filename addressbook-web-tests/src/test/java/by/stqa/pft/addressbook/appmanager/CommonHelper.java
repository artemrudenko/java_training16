package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by artemr on 11/25/2016.
 */
public class CommonHelper {
  private FirefoxDriver wd;

  public CommonHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void setInputValue(By locator, String value) {
    wd.findElement(locator).click();
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(value);
  }

  public void selectValue(By locator, String value) {
    Select select = new Select(wd.findElement(locator));
    // TODO add check on option persistence
    select.selectByVisibleText(value);
  }

}
