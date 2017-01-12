/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.mantis.tests;

import by.stqa.pft.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",
          BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  protected void tearDown() {
    app.stop();
  }

}
