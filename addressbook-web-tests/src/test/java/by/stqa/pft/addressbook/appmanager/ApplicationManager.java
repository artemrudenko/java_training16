/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.appmanager;


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
  private SessionHelper sessionHelper;

  public void init() {
    DesiredCapabilities caps = new DesiredCapabilities();
    FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files (x86)\\Mozilla Firefox ESR\\firefox.exe"));
    wd = new FirefoxDriver(bin, new FirefoxProfile(), caps);
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
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

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

}
