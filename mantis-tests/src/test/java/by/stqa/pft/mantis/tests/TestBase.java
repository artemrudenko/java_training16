/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import by.stqa.pft.mantis.appmanager.ApplicationManager;
import by.stqa.pft.mantis.model.MailMessage;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;


public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",
          BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"),
              "config_inc.php",
             "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  protected void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak",
                       "config_inc.php");
    app.stop();
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL(app.getProperty("web.baseUrl") + app.getProperty("web.soapUrl")));
    IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
    return !issue.getStatus().getName().equals("closed");
  }
}
