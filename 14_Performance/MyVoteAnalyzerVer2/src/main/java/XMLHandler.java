import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private String name;
    private String birthDay;

    public XMLHandler() {
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                name = attributes.getValue("name");
                birthDay = attributes.getValue("birthDay");

            } else if (qName.equals("visit") && name != null && birthDay != null) {
                DBConnection.countVoter(name, birthDay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            name = null;
            birthDay = null;
        }

    }

}