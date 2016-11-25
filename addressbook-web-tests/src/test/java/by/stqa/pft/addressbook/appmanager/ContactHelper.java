package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by artemr on 11/25/2016.
 */
public class ContactHelper {
  private FirefoxDriver wd;
  private CommonHelper commonHelper;

  public ContactHelper(FirefoxDriver wd, CommonHelper commonHelper) {
    this.wd = wd;
    this.commonHelper = commonHelper;
  }

  public void submitContactCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void fillContactForm(ContactData contactData) {
    commonHelper.setInputValue(By.name("firstname"), contactData.getFirstname());
    commonHelper.setInputValue(By.name("middlename"), contactData.getMiddlename());
    commonHelper.setInputValue(By.name("lastname"), contactData.getLastname());
    commonHelper.setInputValue(By.name("nickname"), contactData.getNickname());
    commonHelper.setInputValue(By.name("title"), contactData.getTitle());
    commonHelper.setInputValue(By.name("company"), contactData.getCompany());
    commonHelper.setInputValue(By.name("address"), contactData.getAddress());
    commonHelper.setInputValue(By.name("home"), contactData.getHome());
    commonHelper.setInputValue(By.name("mobile"), contactData.getMobile());
    commonHelper.setInputValue(By.name("work"), contactData.getWork());
    commonHelper.setInputValue(By.name("fax"), contactData.getFax());
    commonHelper.setInputValue(By.name("email"), contactData.getEmail());
    commonHelper.setInputValue(By.name("email2"), contactData.getEmail2());
    commonHelper.setInputValue(By.name("email3"), contactData.getEmail3());
    commonHelper.setInputValue(By.name("homepage"), contactData.getHomepage());

    String[] birtday = contactData.getBirthday().split("-");
    commonHelper.selectValue(By.name("bday"), birtday[0]);
    commonHelper.selectValue(By.name("bmonth"), birtday[1]);
    commonHelper.setInputValue(By.name("byear"), birtday[2]);

    String[] anniversary = contactData.getAnniversary().split("-");
    commonHelper.selectValue(By.name("aday"), anniversary[0]);
    commonHelper.selectValue(By.name("amonth"), anniversary[1]);
    commonHelper.setInputValue(By.name("ayear"), anniversary[2]);

    commonHelper.selectValue(By.name("new_group"), contactData.getGroup());
    commonHelper.setInputValue(By.name("address2"), contactData.getAddress2());
    commonHelper.setInputValue(By.name("phone2"), contactData.getPhone2());
    commonHelper.setInputValue(By.name("notes"), contactData.getNotes());
  }

  public void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }
}
