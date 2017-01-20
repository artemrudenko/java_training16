package by.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by artemr on 1/20/2017.
 */
public class GeoIpServiceTests {
  @Test
  public void testMyIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("212.98.173.235");
    assertEquals(geoIP.getCountryCode(), "BLR");
  }

  @Test
  public void testInvalidIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("212.98.173.xxx");
    assertEquals(geoIP.getReturnCodeDetails(), "Invalid IP address");
  }

}
