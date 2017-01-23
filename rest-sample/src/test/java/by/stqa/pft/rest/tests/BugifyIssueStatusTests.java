package by.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;

/**
 * Created by artemr on 1/23/2017.
 */
public class BugifyIssueStatusTests extends TestBase {
  private int issueId = 3;

  @BeforeClass
  public void init() throws IOException {
    // 3 is closed state
    if(!app.rest().getIssueStatus(issueId).equals("Closed")){
      app.rest().setIssueState(issueId, "3");
    }
  }

  @Test(expectedExceptions = SkipException.class)
  public void testToBeSkipped() throws IOException {
    skipIfNotFixed(1);
  }

  @Test
  public void testNotToBeSkipped() throws IOException {
    skipIfNotFixed(issueId);
  }

}
