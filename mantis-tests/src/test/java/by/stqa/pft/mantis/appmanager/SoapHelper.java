package by.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import by.stqa.pft.mantis.model.Issue;
import by.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by artemr on 1/23/2017.
 */
public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"),
            app.getProperty("web.adminPassword"));
    return Arrays.stream(projects)
            .map((p)-> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
              .getMantisConnectPort(new URL(app.getProperty("web.baseUrl") + app.getProperty("web.soapUrl")));
  }

  public void addProject(String name) throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData projectData = new ProjectData();
    projectData.setName(name);
    mc.mc_project_add(app.getProperty("web.adminLogin"),
            app.getProperty("web.adminPassword"), projectData);
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String login = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    String[] categories = mc.mc_project_get_categories(login, password,
            BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),
            issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(login, password, issueData);
    IssueData createdIssuedData = mc.mc_issue_get(login, password, issueId);
    return new Issue().withId(createdIssuedData.getId().intValue())
            .withSummary(createdIssuedData.getSummary())
            .withDescription(createdIssuedData.getDescription())
            .withProject(new Project().withId(createdIssuedData.getProject().getId().intValue())
                    .withName(createdIssuedData.getProject().getName()));
  }
}
