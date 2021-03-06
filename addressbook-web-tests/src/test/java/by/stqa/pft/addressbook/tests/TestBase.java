/**
 * Created by artemr on 11/25/2016.
 */

package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.appmanager.ApplicationManager;
import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import by.stqa.pft.addressbook.model.GroupData;
import by.stqa.pft.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

@Listeners(MyTestListener.class)
public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",
          BrowserType.CHROME));

  @BeforeSuite
  public void setUp(ITestContext context) throws Exception {
    app.init();
    context.setAttribute("app", app);
    app.goTo().groupPage();
    app.group().deleteAll();
  }

  @AfterSuite(alwaysRun = true)
  protected void tearDown() {
    System.out.println("ALL IS OK!");
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, equalTo(dbContacts.stream()
              .map((c) -> new ContactData().withId(c.getId())
                      .withFirstname(c.getFirstname())
                      .withLastname(c.getLastname())
                      .withAddress(c.getAddress().replaceAll("\r\n", "\n"))
                      .withAllPhones(c.getAllPhones())
                      .withAllEmails(c.getAllEmails()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactInGroupContactsListUI(GroupData group, ContactData contact) {
    if(Boolean.getBoolean("verifyUI")) {
      app.contact().loadGroupContacts(group);
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, hasItem(new ContactData().withId(contact.getId())
              .withFirstname(contact.getFirstname())
              .withLastname(contact.getLastname())
              .withAddress(contact.getAddress().replaceAll("\r\n", "\n"))
              .withAllPhones(contact.getAllPhones())
              .withAllEmails(contact.getAllEmails())));
    }
  }

  public void verifyContactNotInGroupContactsListUI(GroupData group, ContactData contact) {
    if(Boolean.getBoolean("verifyUI")) {
      app.contact().loadGroupContacts(group);
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, not(hasItem(new ContactData().withId(contact.getId())
              .withFirstname(contact.getFirstname())
              .withLastname(contact.getLastname())
              .withAddress(contact.getAddress().replaceAll("\r\n", "\n"))
              .withAllPhones(contact.getAllPhones())
              .withAllEmails(contact.getAllEmails()))));
    }
  }

  public void verifyGroupContactsListUI(GroupData group, ContactData contact, boolean contains){
    if(contains){
      verifyContactInGroupContactsListUI(group, contact);
    }else {
      verifyContactNotInGroupContactsListUI(group, contact);
    }
  }

}
