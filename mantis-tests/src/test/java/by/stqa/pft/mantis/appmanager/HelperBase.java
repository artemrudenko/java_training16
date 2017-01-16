/**
 * Created by artemr on 11/25/2016.
 */
package by.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;


public class HelperBase {
  protected ApplicationManager app;
  protected WebDriver wd;
  protected WebDriverWait wait;

  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();
    this.wait = new WebDriverWait(wd, 60);
  }

  public void type(By locator, String text) {
    if (text != null) {
      click(locator);
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!existingText.equals(text)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  public void attach(By locator, File file) {
    if (file != null) {
      wd.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

  public void select(By locator, String text) {
    if (text == null) {
      return;
    }
    Select select = new Select(wd.findElement(locator));
    WebElement option = select.getFirstSelectedOption();
    if (!option.getText().equals(text)) {
      select.selectByVisibleText(text);
    }
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void closeAlert(boolean confirm) {
    if (confirm) {
      wd.switchTo().alert().accept();
    } else {
      wd.switchTo().alert().dismiss();
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  public void waitElementVisible(By locator) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }
}
