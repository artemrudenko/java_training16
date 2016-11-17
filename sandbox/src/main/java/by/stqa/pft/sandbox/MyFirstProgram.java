package by.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String args[]) {
    System.out.println("Started....");
    for (int i = 0; i < 10; i++) {
      System.out.println("It's alive!!! Attempt: " + i);
    }
    System.out.println("Stopped!");
    double l = 10;
    System.out.println("Square area is: " + area(l) + " Side len is: " + l);
    double a = 3;
    double b = 5;
    System.out.println("Rect with a = " + a + " and b = " + b + " has area = " + area(a, b));

  }
  public static double area(double len){
    return len * len;
  }
  public static double area(double a, double b){
    return a * b;
  }

}
