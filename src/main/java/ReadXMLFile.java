import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by masinogns on 2017. 10. 25..
 */

/**
 * XML을 읽어와 필요한 부분만 읽어서 ArrayList<ArrayList<String>>으로 getValue할 수 있어
 * 편리하게 MySQL에 넣을 수 있다
 */
public class ReadXMLFile {
    private File fileXML;
    private String rootTagName;
    private ArrayList<String> wannaTagNames;
    private ArrayList<ArrayList<String>> result;

    public ReadXMLFile(String filePath, String rootTagName, ArrayList<String> wannaTagNames) {
        fileXML = new File(filePath);
        this.rootTagName = rootTagName;
        this.wannaTagNames = wannaTagNames;

        result = new ArrayList<ArrayList<String>>();
    }

    /**
     * 사용방법
     * 1. 객체를 생성한다
     * 2. 파일의 경로를 입력한다
     * 3. 파일의 루트 태그 이름을 입력한다
     * 4. 파일의 루트 태그 아래의 여러 태그 중에서 원하는 태그 이름들을 입력한다
     * 5. ArrayList<ArrayList<String>>으로 결과가 나온다
     * 6. 결과로 나온 result를 사용하면 된다
     * @param args
     */
    public static void main(String[] args) {
        String filePath = "/Users/masinogns/Atom/neoInformation/서귀포시_민박펜션업소.xml";
        String rootTagName = "Row";
        ArrayList<String> waanaTagNames = new ArrayList<String>(Arrays.asList(
                "업소명", "연락처", "소재지도로명주소"
        ));
        ArrayList<ArrayList<String>> result;

        // 방법 2. 생성자를 만들 때, 필요 파라미터들을 넣어 실행하는 방법
        ReadXMLFile application = new ReadXMLFile(filePath, rootTagName, waanaTagNames);
        result = application.getValue();
//        result = findBracket(result);
//        application.printRun(result);

//        방법 1. 생성자를 만들어서 일일이 setting해주는 방법
//        ReadXMLFile app = new ReadXMLFile();
//        application.setFilePath(filePath);
//        application.setRootTagName(rootTagName);
//        application.setWannaTagNames(waanaTagNames);
//        result = application.getValue();
    }

    public ArrayList<ArrayList<String>> findBracket(ArrayList<ArrayList<String>> result) {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();

        for (ArrayList<String> oneThing : result){
            ArrayList<String> item = new ArrayList<String>();

            String one = oneThing.get(0);
            String two = oneThing.get(1);
            String three = oneThing.get(2);

            if (three.contains("(")){
                int index = three.indexOf("(");
                three = three.substring(0, index);
            }

            item.add(0, one);
            item.add(1, two);
            item.add(2, three);

            ret.add(item);

            System.out.println(one+two+three);
        }

        return ret;
    }

    private void printRun(ArrayList<ArrayList<String>> result) {
        int i = 0;
        for (ArrayList<String> oneThing : this.result){


            String one = oneThing.get(0);
            String two = oneThing.get(1);
            String three = oneThing.get(2);
            if (three.contains("("))
                System.out.println(one);
            i++;
            System.out.println();
        }
    }

    /**
     *
     * @param fileXML
     * @param rootTagName
     * @param elements
     */
    private void setValue(File fileXML, String rootTagName, ArrayList<String> elements) {
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileXML);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName(rootTagName);

            ArrayList<String> items;
            for (int index = 0; index < nodeList.getLength(); index++){
                Node node = nodeList.item(index);
                items = new ArrayList<String>();

                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    for (String tag : elements){

                        items.add(getTagValue(tag, element));
//                        System.out.println(tag + " : " + getTagValue(tag, element));
                    }

                    result.add(index, items);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param tag
     * @param element
     * @return
     */
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        // NullPointerException을 해결하기 위해 넣었다
        if (nodeList.getLength() == 0)
            return null;

        Node value = (Node)nodeList.item(0);
        return value.getNodeValue();
    }

    public ReadXMLFile() {
        result = new ArrayList<ArrayList<String>>();
    }

    public void setFilePath(String filePath) {
        this.fileXML = new File(filePath);
    }

    public void setRootTagName(String rootTagName) {
        this.rootTagName = rootTagName;
    }

    public void setWannaTagNames(ArrayList<String> wannaTagNames) {
        this.wannaTagNames = wannaTagNames;
    }


    public ArrayList<ArrayList<String>> getValue() {
        this.setValue(
                this.getFileXML(),
                this.getRootTagName(),
                this.getWannaTagNames()
        );

        return this.result;
    }

    public ArrayList<String> getWannaTagNames() {
        return this.wannaTagNames;
    }

    public File getFileXML() {
        return this.fileXML;
    }

    public String getRootTagName() {
        return this.rootTagName;
    }
}
