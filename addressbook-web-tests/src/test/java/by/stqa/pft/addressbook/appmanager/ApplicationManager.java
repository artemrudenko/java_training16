/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  FirefoxDriver wd;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private CommonHelper commonHelper;
  private SessionHelper sessionHelper;

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() {
    DesiredCapabilities caps = new DesiredCapabilities();
    FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files (x86)\\Mozilla Firefox ESR\\firefox.exe"));
    wd = new FirefoxDriver(bin, new FirefoxProfile(), caps);
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    commonHelper = new CommonHelper(wd);
    groupHelper = new GroupHelper(wd, commonHelper);
    contactHelper = new ContactHelper(wd, commonHelper);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd, commonHelper);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
  public CommonHelper getCommonHelper() {
    return commonHelper;
  }
  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

}
