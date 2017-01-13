package by.stqa.pft.mantis.model;

/**
 * Created by artemr on 1/13/2017.
 */
public class MailMessage {
  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }
}
