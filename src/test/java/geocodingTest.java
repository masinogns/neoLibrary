import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by masinogns on 2017. 11. 1..
 */
public class geocodingTest {

    @Test
    public void test() throws Exception {
        geocoding bb = new geocoding();
        
        String path = "\"제주특별자치도 제주시 거로중길 28\"";
        bb.run(path);

        System.out.println("result location");
        System.out.println("lng : "+bb.getLng());
        System.out.println("lat : "+bb.getLat());
    }
}