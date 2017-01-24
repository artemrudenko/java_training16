package by.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by artemr on 1/24/2017.
 */
public class GithubTests {
  @Test
  public void testCommits() throws IOException {
    String token = "token";
    Github github = new RtGithub(token);
    RepoCommits commits = github.repos().get(new Coordinates.Simple("artemrudenko", "java_training16")).commits();
    for(RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
