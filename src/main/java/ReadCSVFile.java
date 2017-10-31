/**
 * Created by masinogns on 2017. 10. 31..
 */


import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadCSVFile {

    public ArrayList<ArrayList<String>> reader(String filePath) throws IOException {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> item;
        CSVReader reader = new CSVReader(new FileReader(filePath));

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            item = new ArrayList<String>();
            // nextLine[] is an array of values from the line
//            System.out.println("1." + nextLine[0]);
//            System.out.println("2." + nextLine[1]);
//            System.out.println("3." + nextLine[2]);
//            System.out.println("4." + nextLine[3]);
//            System.out.println("5." + nextLine[4]);
//            System.out.println("6." + nextLine[5]);
//            System.out.println("7." + nextLine[6]);
//            System.out.println();

//            이것은 aaa일 때 향토 음식점 대상
            item.add(nextLine[2]);
            item.add(nextLine[6]);
            item.add(nextLine[4]);

//            // bbb일 때
//            item.add(nextLine[5]);
//            item.add(nextLine[7]);
//            item.add(nextLine[6]);
            result.add(item);
        }

        return result;
    }

    public void run(){

    }

//    public static void main(String[] args) {
//        ReadCSVFile csv= new ReadCSVFile();
//        String s = "/Users/masinogns/Atom/neoInformation/";
//        String fileName = "aaa";
//        String fileType = ".csv";
//        String path = s + fileName + fileType;
//
//        try {
//            csv.reader(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
