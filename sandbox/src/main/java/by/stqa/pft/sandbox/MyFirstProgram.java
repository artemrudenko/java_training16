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

    Point pA = new Point(1, 3);
    Point pB = new Point(2, 12);
    System.out.println("a) Distance with function is: " + distance(pA, pB));
    System.out.println("b) Distance with method is: " + pA.distance(pB));

  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
  }
}
