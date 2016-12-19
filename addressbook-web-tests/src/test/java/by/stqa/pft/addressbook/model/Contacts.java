package by.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by artemr on 12/19/2016.
 */
public class Contacts  extends ForwardingSet<ContactData> {
  private Set<ContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData group){
    Contacts groups = new Contacts(this);
    groups.remove(group);
    return groups;
  }

  public Contacts withModified(ContactData contact){
    Contacts contacts = new Contacts();
    for(ContactData g: this.delegate){
      if(g.getId() != contact.getId()){
        contacts.add(g);
      }
    }
    contacts.add(contact);
    return contacts;
  }
}
