/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseAddressbookTest {
  FirefoxDriver wd;

  @BeforeMethod
  public void setUp() throws Exception {
    DesiredCapabilities caps = new DesiredCapabilities();
    FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files (x86)\\Mozilla Firefox ESR\\firefox.exe"));
    wd = new FirefoxDriver(bin, new FirefoxProfile(), caps);
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  public void login(String username, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  public void setInputValue(By locator, String value){
    wd.findElement(locator).click();
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(value);
  }

  public void selectValue(By locator, String value){
    Select select = new Select(wd.findElement(locator));
    // TODO add check on option persistence
    select.selectByVisibleText(value);
  }

  @AfterMethod
  public void tearDown() {
    wd.quit();
  }

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
