import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Loader {

    public static void main(String[] args) throws Exception {
        String fileName = "MyVoteAnalyzer/res/data-1572M.xml";
        long start = System.currentTimeMillis();
        XMLHandler handler = getSaxHandler(fileName);
        System.out.println(DBConnection.getInsertQueue().size() + " - size Queue");
        DBConnection.insertToBD();
        System.out.println(DBConnection.getInsertQueue().size() + " - size Queue");
        System.out.println(System.currentTimeMillis() - start + " ms - Результат SAX парсера");
        long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Печать результатов");
        System.out.println("Memory Mgb Sax парсер = " + memory / 1048576);
        DBConnection.printVoterCounts();

    }

    private static XMLHandler getSaxHandler(String fileName) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        File file = new File(fileName);
        try (InputStream inputStream = new FileInputStream(file);
             //InputStream inputStream= new BufferedInputStream(new FileInputStream(file));
             Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))
        ) {
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");
            /*parser.parse(new File(fileName), handler);*/
            parser.parse(is, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return handler;
    }

}