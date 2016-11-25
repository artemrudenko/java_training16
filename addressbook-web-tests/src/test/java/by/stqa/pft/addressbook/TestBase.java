/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TestBase {
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

  protected void login(String username, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  protected void setInputValue(By locator, String value){
    wd.findElement(locator).click();
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(value);
  }

  protected void selectValue(By locator, String value){
    Select select = new Select(wd.findElement(locator));
    // TODO add check on option persistence
    select.selectByVisibleText(value);
  }

  @AfterMethod
  protected void tearDown() {
    wd.quit();
  }

  protected static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  protected void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  protected void fillGroupForm(GroupData groupData) {
    setInputValue(By.name("group_name"), groupData.getName());
    setInputValue(By.name("group_header"), groupData.getHeader());
    setInputValue(By.name("group_footer"), groupData.getFooter());
  }

  protected void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  protected void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  protected void returnToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  protected void submitContactCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  protected void fillContactForm(ContactData contactData) {
    setInputValue(By.name("firstname"), contactData.getFirstname());
    setInputValue(By.name("middlename"), contactData.getMiddlename());
    setInputValue(By.name("lastname"), contactData.getLastname());
    setInputValue(By.name("nickname"), contactData.getNickname());
    setInputValue(By.name("title"), contactData.getTitle());
    setInputValue(By.name("company"), contactData.getCompany());
    setInputValue(By.name("address"), contactData.getAddress());
    setInputValue(By.name("home"), contactData.getHome());
    setInputValue(By.name("mobile"), contactData.getMobile());
    setInputValue(By.name("work"), contactData.getWork());
    setInputValue(By.name("fax"), contactData.getFax());
    setInputValue(By.name("email"), contactData.getEmail());
    setInputValue(By.name("email2"), contactData.getEmail2());
    setInputValue(By.name("email3"), contactData.getEmail3());
    setInputValue(By.name("homepage"), contactData.getHomepage());

    String[] birtday = contactData.getBirthday().split("-");
    selectValue(By.name("bday"), birtday[0]);
    selectValue(By.name("bmonth"), birtday[1]);
    setInputValue(By.name("byear"), birtday[2]);

    String[] anniversary = contactData.getAnniversary().split("-");
    selectValue(By.name("aday"), anniversary[0]);
    selectValue(By.name("amonth"), anniversary[1]);
    setInputValue(By.name("ayear"), anniversary[2]);

    selectValue(By.name("new_group"), contactData.getGroup());
    setInputValue(By.name("address2"), contactData.getAddress2());
    setInputValue(By.name("phone2"), contactData.getPhone2());
    setInputValue(By.name("notes"), contactData.getNotes());
  }

  protected void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  protected void deleteSelectedGroups() {
      wd.findElement(By.name("delete")).click();
  }

  protected void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
  }
}
