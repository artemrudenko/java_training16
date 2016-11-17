package by.stqa.pft.sandbox;

/**
 * Created by artemr on 11/17/2016.
 */
public class Rectangle {

  public double a;
  public double b;

  public Rectangle(double a, double b){
    this.a = a;
    this.b = b;
  }
  public double area(){
    return this.a * this.b;
  }
}
