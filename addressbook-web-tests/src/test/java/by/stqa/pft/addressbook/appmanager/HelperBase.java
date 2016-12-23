/**
 * Created by artemr on 11/25/2016.
 */
package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;


public class HelperBase {
  public WebDriver wd;
  public WebDriverWait wait;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
    this.wait = new WebDriverWait(wd, 60);
  }

  public void type(By locator, String text) {
    if (text != null) {
      wd.findElement(locator).click();
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

  public String[] getTableRowsText(By tableLocator) {
    WebElement table = wd.findElement(tableLocator);
    List<WebElement> rows = table.findElements(By.name("entry"));
    String[] out = new String[rows.size()];
    for (int i = 0; i < rows.size(); i++) {
      out[i] = rows.get(i).getText();
//      ++i;
    }
    return out;
  }

  public void clickTableElement(By tableLocator, String text, By elementLocator) {
    WebElement table = wd.findElement(tableLocator);
    List<WebElement> rows = table.findElements(By.name("entry"));
    for (WebElement row : rows) {
      // think on better condition here
      if (row.getText().contains(text)) {
        row.findElement(elementLocator).click();
        break;
      }
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
