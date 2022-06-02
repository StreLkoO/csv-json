import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class XMLParser {
    private static XMLParser instance;

    private XMLParser() {
    }

    public static XMLParser get() {
        if (instance == null) {
            instance = new XMLParser();
        }
        return instance;
    }

    public List<Employee> parseXML(String file) {
        List<Employee> staff = new LinkedList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(file));
            NodeList nodeList = document.getElementsByTagName("employee");
            for (int i = 0; i < nodeList.getLength(); i++) {
                staff.add(getEmployee(nodeList.item(i)));
            }
            return staff;
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Employee getEmployee(Node node) {
        Employee e = new Employee();
        if (Node.ELEMENT_NODE == node.getNodeType()) {
            Element element = (Element) node;
            e.id = Long.parseLong(getTagValue("id", element));
            e.firstName = getTagValue("firstName", element);
            e.lastName = getTagValue("lastName", element);
            e.country = getTagValue("country", element);
            e.age = Integer.parseInt(getTagValue("age", element));
        }
        return e;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }


}
