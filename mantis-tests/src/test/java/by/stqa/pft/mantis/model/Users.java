package by.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by artemr on 1/16/2017.
 */
public class Users extends ForwardingSet<UserData> {
  private Set<UserData> delegate;

  public Users(Users users) {
    this.delegate = new HashSet<UserData>(users.delegate);
  }

  public Users() {
    this.delegate = new HashSet<UserData>();
  }

  public Users(Collection<UserData> contacts) {
    this.delegate = new HashSet<UserData>(contacts);
  }

  @Override
  protected Set<UserData> delegate() {
    return delegate;
  }

  public Users withAdded(UserData contact) {
    Users contacts = new Users(this);
    contacts.add(contact);
    return contacts;
  }

  public Users without(UserData group) {
    Users groups = new Users(this);
    groups.remove(group);
    return groups;
  }

  public Users withModified(UserData contact) {
    Users contacts = new Users();
    for (UserData g : this.delegate) {
      if (g.getId() != contact.getId()) {
        contacts.add(g);
      }
    }
    contacts.add(contact);
    return contacts;
  }
}
