package by.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by artemr on 12/8/2016.
 */
public class Collections {
  public static void main(String[] args){
    String[] langs = {"Java", "C#", "Python", "PHP"};
    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
    for(String lang: languages){
      System.out.println("I want to learn " + lang);
    }

  }
}
