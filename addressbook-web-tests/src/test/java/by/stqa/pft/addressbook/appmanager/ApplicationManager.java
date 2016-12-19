/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private int timeout;
  WebDriver wd;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    this.timeout = 0;
  }

  public void init() {
    DesiredCapabilities caps = new DesiredCapabilities();
    switch (browser) {
      case BrowserType.FIREFOX:
        FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files (x86)\\Mozilla Firefox ESR\\firefox.exe"));
        wd = new FirefoxDriver(bin, new FirefoxProfile(), caps);
        break;
      case BrowserType.CHROME:
        wd = new ChromeDriver(caps);
        break;
      case BrowserType.IE:
        wd = new InternetExplorerDriver(caps);
        break;
    }

    wd.manage().timeouts().implicitlyWait(this.timeout, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    SessionHelper sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

}
