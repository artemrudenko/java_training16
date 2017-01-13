package by.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by artemr on 1/13/2017.
 */
public class RegistrationTests extends TestBase {
  @Test
  public void testRegistration(){
    app.registration().start("user1", "user1@localhost.localdomain");
  }
}
