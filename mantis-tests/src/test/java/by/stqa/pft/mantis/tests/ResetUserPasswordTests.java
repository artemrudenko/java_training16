package by.stqa.pft.mantis.tests;

import by.stqa.pft.mantis.model.MailMessage;
import by.stqa.pft.mantis.model.UserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by artemr on 1/16/2017.
 */
public class ResetUserPasswordTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() throws IOException {
    app.mail().start();
    app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    if(app.db().users().size() == 0){
      app.goTo().manageUsersPage();
      app.users().create(new UserData().withUsername("TestUser").withEmail("test@gmail.com"));
    }
    app.goTo().manageUsersPage();
  }

  @Test
  public void testResetUserPassword() throws IOException, MessagingException {
    String newPassword = String.format("%s", System.currentTimeMillis());
    UserData before = app.db().users().iterator().next();
    app.users().resetUserPassword(before);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, before.getEmail());
    app.registration().finish(confirmationLink, newPassword);
    assertTrue(app.newSession().login(before.getUsername(), newPassword));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }

}
