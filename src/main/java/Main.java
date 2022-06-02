
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Main {
    static final String[] COLUMNMAPPING = {"id", "firstName", "lastName", "country", "age"};
    static final String FILENAMECSV = "data.csv";
    static final String CSVTOJSONFILE = "new_data.json";
    static final String FILENAMEXML = "data.xml";
    static final String XMLTOJSONFILE = "new_data2.json";

    public static void main(String[] args) {

        CSVParser csvParser = CSVParser.get();
        List<Employee> staff = csvParser.parseCSV(COLUMNMAPPING, FILENAMECSV);
        JsonWriter jsonWriter = JsonWriter.get();
        jsonWriter.writeListInJson(staff, CSVTOJSONFILE);

        XMLParser xmlParser = XMLParser.get();
        List<Employee> staffFromXML = xmlParser.parseXML(FILENAMEXML);
        jsonWriter.writeListInJson(staffFromXML, XMLTOJSONFILE);

        JsonParser jsonParser = JsonParser.get();
        List<Employee> staffFromJson = jsonParser.JsonFileToList(XMLTOJSONFILE);
        System.out.println(staffFromJson);

    }


}
