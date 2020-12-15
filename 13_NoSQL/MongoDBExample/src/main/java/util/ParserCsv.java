package util;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ParserCsv {

    public static List<String[]> getArraysDataList(String pathFile, char csvSeparator, char quote) {
        List<String[]> listArray = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(pathFile), csvSeparator, quote, 0)) {
            //Read CSV line by line and use the string array as you want
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                //Verifying the read data here
                listArray.add(nextLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listArray;
    }
}
