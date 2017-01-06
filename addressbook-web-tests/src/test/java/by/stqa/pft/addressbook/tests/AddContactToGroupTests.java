package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import by.stqa.pft.addressbook.model.GroupData;
import by.stqa.pft.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by artemr on 1/4/2017.
 */
public class AddContactToGroupTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("MyGroup"));
    }
    app.goTo().homePage();
    if(app.db().contacts().size() == 0){
      app.contact().create(app.contact().generate());
    }
  }

  @Test
  public void testAddContactToGroup(){
    GroupData group = app.db().groups().iterator().next();
    Contacts before = app.db().contacts();
    ContactData toUpdate = before.iterator().next();
    System.out.println(toUpdate);
    app.contact().addContactToGroup(toUpdate, group);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withModified(toUpdate.inGroup(group))));
    verifyGroupContactsListUI(group, toUpdate, true);
  }
}
