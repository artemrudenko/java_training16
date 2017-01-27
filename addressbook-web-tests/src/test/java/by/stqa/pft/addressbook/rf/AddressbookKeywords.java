package by.stqa.pft.addressbook.rf;

import by.stqa.pft.addressbook.appmanager.ApplicationManager;
import by.stqa.pft.addressbook.model.GroupData;
import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;

/**
 * Created by artemr on 1/27/2017.
 */
public class AddressbookKeywords {

  public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

  private ApplicationManager app;

  public void initApplicationManager() throws IOException {
    app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    app.init();
  }

  public void stopApplicationManager(){
    app.stop();
    app = null;
  }
  public int getGroupCount(){
    app.goTo().groupPage();
    return app.group().count();
  }

  public void createGroup(String name, String header, String footer){
    app.goTo().groupPage();
    app.group().create(new GroupData().withName(name).withHeader(header).withFooter(footer));
  }
}
