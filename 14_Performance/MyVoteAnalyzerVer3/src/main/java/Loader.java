import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final String RESULT_PATH = String.join(File.separator, System.getProperty("user.dir"),"MyVoteAnalyzerVer3", "res", "");

    public static void main(String[] args) throws Exception {
        String fileName = RESULT_PATH + "data-1572M.xml";
        long start = System.currentTimeMillis();
        XMLHandler handler = getSaxHandler(fileName);
        System.out.println(DBConnection.getInsertQueue().size() + " - size List");
        DBConnection.createDB();
        DBConnection dbConnection = new DBConnection();
        dbConnection.insertToBD();
//        runMultiTread();
        System.out.println(DBConnection.getInsertQueue().size() + " - size List");
        DBConnection dbConnection1 = new DBConnection();
        long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Печать результатов");
        System.out.println("Memory Mgb Sax парсер = " + memory / 1048576);
        dbConnection1.printVoterCounts();
        System.out.println(System.currentTimeMillis() - start + " ms - Результат SAX парсера");
        //не работает
        dbConnection1.updateDB();

    }

    private static void runMultiTread() throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CORES; i++) {
            DBConnection dbConnection = new DBConnection();
            Thread thread = new Thread(dbConnection);
            thread.start();
            threadList.add(thread);
            Thread.sleep(10);
        }
        threadJoin(threadList);
    }

    private static void threadJoin(List<Thread> threadList) {
        for (Thread tr : threadList
        ) {
            try {
                tr.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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