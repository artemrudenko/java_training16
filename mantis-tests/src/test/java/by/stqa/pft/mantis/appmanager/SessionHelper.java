/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SessionHelper extends HelperBase{

  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password){
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }
}
