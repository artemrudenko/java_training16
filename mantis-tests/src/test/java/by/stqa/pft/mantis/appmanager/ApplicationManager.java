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
  private WebDriver wd;

  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mail;
  private DbHelper dbHelper;
  private NavigationHelper goTo;
  private UserHelper users;
  private SessionHelper session;
  private JamesHelper jamesHelper;
  private SoapHelper soapHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();
  }

  public void stop() {
    if (wd != null) {
      wd.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public FtpHelper ftp() {
    if(ftp == null){
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail(){
    if(mail == null){
      mail = new MailHelper(this);
    }
    return mail;
  }

  public DbHelper db(){
    return dbHelper;
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public NavigationHelper goTo(){
    if(goTo == null){
      goTo = new NavigationHelper(this);
    }
    return goTo;
  }

  public UserHelper users(){
    if(users == null){
      users = new UserHelper(this);
    }
    return users;
  }

  public SessionHelper session(){
    if(session == null){
      session = new SessionHelper(this);
    }
    return session;
  }

  public SoapHelper soap(){
    if(soapHelper == null){
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }

  public WebDriver getDriver() {
    if (wd == null) {
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
    return wd;
  }
  public JamesHelper james(){
    if(jamesHelper == null){
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }
}
