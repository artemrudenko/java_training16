package by.stqa.java.lesson1;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by artemr on 11/24/2016.
 */
public class PointTests {
  @Test
  public void testDistance(){
    Point pA = new Point(0, 0);
    Point pB = new Point(0, 7);
    Assert.assertEquals(pA.distance(pB), 7);
  }

  @Test
  public void testTwoEqualPointsDistance(){
    Point pA = new Point(1, 5);
    Point pB = new Point(1, 5);
    Assert.assertEquals(pA.distance(pB), 0.);
  }

  @Test
  public void testNegativePointsDistance(){
    Point pA = new Point(-1, -5);
    Point pB = new Point(-1, 5);
    Assert.assertEquals(pA.distance(pB), 10.);
  }

}
