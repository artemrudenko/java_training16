/**
 * Created by artemr on 11/25/2016.
 */
package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class HelperBase {
  public WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  public void type(By locator, String text) {
    wd.findElement(locator).click();
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!existingText.equals(text)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  public void select(By locator, String text) {
    Select select = new Select(wd.findElement(locator));
    // TODO add check on option persistence
    select.selectByVisibleText(text);
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public String[] getTitles() {
    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    String[] out = new String[elements.size()];
    int i = 0;
    for (WebElement item : elements) {
      out[i] = item.getAttribute("title");
      ++i;
    }
    return out;
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
      ++i;
    }
    return out;
  }

  public void clickTableElement(By tableLocator, String text, By elementLocator) {
    WebElement table = wd.findElement(tableLocator);
    List<WebElement> rows = table.findElements(By.name("entry"));
    for (int i = 0; i < rows.size(); i++) {
      WebElement row = rows.get(i);
      // think on better condition here
      if (row.getText().contains(text)) {
        row.findElement(elementLocator).click();
        break;
      }
    }
  }

  public boolean isElementPresent(By locator) {
    wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    try{
      wd.findElement(locator);
      return true;
    }catch (NoSuchElementException ex){
      return false;
    }finally {
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
  }

}
