import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by masinogns on 2017. 10. 31..
 */
public class ReadCSVFileTest {
    private String s = "/Users/masinogns/Atom/neoInformation/";
    private String fileType = ".csv";
    @Test
    public void input1() throws Exception {
        ReadCSVFile csv= new ReadCSVFile();
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();

        String fileName = "aaa";
        String path = s + fileName + fileType;

        try {
            ret = csv.reader(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}