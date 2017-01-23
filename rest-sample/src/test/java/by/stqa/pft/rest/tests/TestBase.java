/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.rest.tests;

import by.stqa.pft.rest.appmanager.ApplicationManager;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;


public class TestBase {

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  protected static final ApplicationManager app = new ApplicationManager();

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws IOException {
    return !app.rest().getIssueStatus(issueId).equals("Closed");
  }
}
