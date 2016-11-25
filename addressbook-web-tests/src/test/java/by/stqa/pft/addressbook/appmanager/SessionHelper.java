package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by artemr on 11/25/2016.
 */
public class SessionHelper {
  private FirefoxDriver wd;
  private CommonHelper commonHelper;

  public SessionHelper(FirefoxDriver wd, CommonHelper commonHelper) {
    this.wd = wd;
    this.commonHelper = commonHelper;
  }

  public void login(String username, String password) {
    commonHelper.setInputValue(By.name("user"), username);
    commonHelper.setInputValue(By.name("pass"), password);
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

}
