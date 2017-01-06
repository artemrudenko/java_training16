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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  private int timeout;
  WebDriver wd;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser){
    this.browser = browser;
    this.timeout = 0;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String .format("src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();

    DesiredCapabilities caps = new DesiredCapabilities();
    switch (browser) {
      case BrowserType.FIREFOX:
        FirefoxBinary bin = new FirefoxBinary(new File(properties.getProperty("web.firefoxBinary")));
        wd = new FirefoxDriver(bin, new FirefoxProfile(), caps);
        break;
      case BrowserType.CHROME:
        System.setProperty("webdriver.chrome.driver", "d:\\EDUCATION\\SELENIUM\\TOOLS\\chromedriver.exe");
        wd = new ChromeDriver(caps);
        break;
      case BrowserType.IE:
        wd = new InternetExplorerDriver(caps);
        break;
    }

    wd.manage().timeouts().implicitlyWait(this.timeout, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    SessionHelper sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
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

  public DbHelper db(){
    return dbHelper;
  }

}
