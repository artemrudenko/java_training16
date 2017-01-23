package by.stqa.pft.rest.appmanager;

import by.stqa.pft.rest.model.Issue;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.Set;

/**
 * Created by artemr on 1/23/2017.
 */
public class RestHelper {
  private final ApplicationManager app;

  private Executor getExecutor() {
    return Executor.newInstance()
            .auth(app.getProperty("web.restLogin"),
                  app.getProperty("web.restPassword"));
  }

  public RestHelper(ApplicationManager app) {
    this.app = app;
  }

  public String getIssueStatus(int issueId) throws IOException {
    String url = app.getProperty("web.baseUrl") + app.getProperty("web.restUrl") + "/issues/" + issueId + ".json";
    String json = getExecutor()
            .execute(Request.Get(url))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> res = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    assert res.size() == 1;
    return res.iterator().next().getStateName();
  }

  public String setIssueState(int issueId, String state) throws IOException {
    String url = app.getProperty("web.baseUrl") + app.getProperty("web.restUrl") + "/issues/" + issueId + ".json";
    return getExecutor()
            .execute(Request.Post(url)
            .bodyForm(new BasicNameValuePair("method", "update"),
                      new BasicNameValuePair("issue[state]", state)))
            .returnContent().asString();
  }
}
