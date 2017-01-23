package by.stqa.pft.mantis.tests;

import by.stqa.pft.mantis.model.Issue;
import by.stqa.pft.mantis.model.Project;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by artemr on 1/20/2017.
 */
public class SoapTests extends TestBase{
  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for(Project project: projects){
      System.out.println(project.getName());
    }
  }
  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    if(projects.size() < 1){
      app.soap().addProject("Sample Project");
      projects = app.soap().getProjects();
    }
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());

  }

}
