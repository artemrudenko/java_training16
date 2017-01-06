package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import by.stqa.pft.addressbook.model.GroupData;
import by.stqa.pft.addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by artemr on 12/29/2016.
 */
public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper(){
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
   }

  public Groups groups(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public ContactData contactById(int id){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ContactData result = (ContactData) session.createQuery( "from ContactData where id = '" + id + "'" ).getSingleResult();
    session.getTransaction().commit();
    session.close();
    return result;
  }

  public GroupData groupById(int id){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    GroupData result = (GroupData) session.createQuery( "from GroupData where id = '" + id + "'" ).getSingleResult();
    session.getTransaction().commit();
    session.close();
    return result;
  }

}
