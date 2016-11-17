package by.stqa.pft.sandbox;

/**
 * Created by artemr on 11/17/2016.
 */
public class Point {
  int x;
  int y;

  public Point(int X, int Y){
    this.x = X;
    this.y = Y;
  }

  public double distance(Point p){
    return distance(this, p);
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
  }

  public static void main(String[] args) {
    Point pA = new Point(1, 3);
    Point pB = new Point(2, 12);
    System.out.println("a) Distance is: " + distance(pA, pB));
    System.out.println("b) Distance is: " + pA.distance(pB));
  }
}
