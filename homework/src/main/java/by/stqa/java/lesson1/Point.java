package by.stqa.java.lesson1;

/**
 * Created by artemr on 11/17/2016.
 */
public class Point {
  public int x;
  public int y;

  public Point(int x, int y){
    this.x = x;
    this.y = y;
  }

  public double distance(Point p){
    return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
  }
}
