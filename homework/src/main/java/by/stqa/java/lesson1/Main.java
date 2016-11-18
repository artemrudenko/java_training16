package by.stqa.java.lesson1;

public class Main {
  public static void main(String args[]) {

    Point pA = new Point(1, 3);
    Point pB = new Point(2, 12);
    System.out.println("a) Distance with function is: " + distance(pA, pB));
    System.out.println("b) Distance with method is: " + pA.distance(pB));

  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
  }
}
