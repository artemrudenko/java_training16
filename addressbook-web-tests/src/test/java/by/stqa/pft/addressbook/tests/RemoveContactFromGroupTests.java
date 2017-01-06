package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import by.stqa.pft.addressbook.model.GroupData;
import by.stqa.pft.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by artemr on 1/6/2017.
 */
public class RemoveContactFromGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("MyGroup"));
    }

    if(app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.contact().create(app.contact().generate());
    }

    if(app.db().contacts().stream().filter((c)-> c.getGroups().size() != 0).collect(Collectors.toList()).size() == 0){
      app.goTo().homePage();
      GroupData group = app.db().groups().iterator().next();
      ContactData toUpdate = app.db().contacts().iterator().next();
      app.contact().addContactToGroup(toUpdate, group);
    }
    app.goTo().homePage();
  }

  @Test
  public void testRemoveContactFromGroup(){
    Contacts before = app.db().contacts();
    Contacts inGroupsContacts = new Contacts(before.stream().filter((c)-> c.getGroups().size() != 0).collect(Collectors.toList()));
    ContactData contact = inGroupsContacts.iterator().next();
    Groups groupsBefore = contact.getGroups();
    GroupData group = groupsBefore.iterator().next();
    app.contact().removeContactFromGroup(contact, group);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withModified(contact.withGroups(groupsBefore.without(group)))));
    verifyGroupContactsListUI(group, contact, false);
  }
}
