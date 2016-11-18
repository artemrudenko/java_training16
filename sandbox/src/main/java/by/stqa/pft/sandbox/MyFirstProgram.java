package by.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String args[]) {
    System.out.println("Started....");

    for (int i = 0; i < 2; i++) {
      System.out.println("It's alive!!! Attempt: " + i);
    }
    System.out.println("Stopped!");
    Square sq = new Square(5);
    System.out.println("Square area is: " + sq.area() + " Side len is: " + sq.l);
    Rectangle rc = new Rectangle(3, 5);
    System.out.println("Rect with a = " + rc.a + " and b = " + rc.b + " has area = " + rc.area());
  }
}
