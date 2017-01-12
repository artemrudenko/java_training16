/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.mantis.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;

  private String browser;

  public ApplicationManager(String browser){
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String .format("src/test/resources/%s.properties", target))));

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

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
  }

  public void stop() {
    wd.quit();
  }

}
