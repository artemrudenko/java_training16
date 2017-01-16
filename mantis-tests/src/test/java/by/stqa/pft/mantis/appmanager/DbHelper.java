package by.stqa.pft.mantis.appmanager;

import by.stqa.pft.mantis.model.UserData;
import by.stqa.pft.mantis.model.Users;
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

  public Users users(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData where enabled = 1 and username != 'administrator'" ).list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public UserData userById(int id){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    UserData result = (UserData) session.createQuery( "from UserData where id = '" + id + "'" ).getSingleResult();
    session.getTransaction().commit();
    session.close();
    return result;
  }

}
