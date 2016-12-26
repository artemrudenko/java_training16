package by.stqa.pft.addressbook.generators;

import by.stqa.pft.addressbook.model.ContactData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static io.codearte.jfairy.producer.person.PersonProperties.withCompany;

/**
 * Created by artemr on 12/26/2016.
 */
public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contacts count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }
  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if(format.equals("csv")){
      saveAsCsv(contacts, new File(file));
    }else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    }else if (format.equals("json")){
      saveAsJson(contacts, new File(file));
    }else {
      System.out.println("Unrecognized format " + format);
    }
  }
  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for(ContactData contact: contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
              contact.getAddress(), contact.getHomePhone(), contact.getWorkPhone(), contact.getMobilePhone(),
              contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    Fairy fairy = Fairy.create();
    List<ContactData> contacts = new ArrayList<ContactData>();
    for(int i =0; i< count; i++){
      Company company = fairy.company();
      Person person = fairy.person(withCompany(company));
      Address address = person.getAddress();
      String fullAddress = address.getCity() + "\n" + address.getPostalCode() + "\n" + address.getAddressLine1();
      contacts.add(new ContactData()
              .withFirstname(person.getFirstName())
              .withLastname(person.getLastName())
              .withAddress(fullAddress)
              .withHomePhone(person.getTelephoneNumber())
              .withWorkPhone(fairy.person().getTelephoneNumber())
              .withMobilePhone(fairy.person().getTelephoneNumber())
              .withEmail(person.getEmail())
              .withEmail2(fairy.person().getEmail())
              .withEmail3(fairy.person().getEmail()));
    }
    return contacts;
  }
}
