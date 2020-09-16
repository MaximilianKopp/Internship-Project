package org.hs.mainz.praktikum.finalversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

public class CSVReader {

    private static String path = null;

    public static void loadCSV() {
        try {
            JFileChooser jfcLoadCSV = new JFileChooser();
            jfcLoadCSV.showOpenDialog(null);
            path = jfcLoadCSV.getSelectedFile().getAbsolutePath();
            System.out.println(path);
        } catch (NullPointerException e) {
            e.getStackTrace();
        }

    }

    public static List<DataStorage> storage() {

        List<DataStorage> dataList = new ArrayList<>();
        BufferedReader input;
        try {
            input = Files.newBufferedReader(Paths.get(path));
            // "C:\\Users\\s7a38865\\Desktop\\sampleAtomisiert5.csv"
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreEmptyLines(true)
                    .parse(input);

            for (CSVRecord record : records) {
                int id = Integer.parseInt(record.get("ID"));
                String foreName = record.get("Vorname");
                String patronym = record.get("Patronym");
                String lastName = record.get("Nachname");
                String dateGreg = record.get("Datierung gregorianisch");
                Integer dateGregYear = NumberUtils.toInt(record.get("Jahresdatierung gregorianisch"));
                Integer dateJewish = NumberUtils.toInt(record.get("Datierung juedisch"));
                String monthName = record.get("Monatsname");
                String text = record.get("Text");
                String url = record.get("URL");
                String idEpidat = record.get("ID_Epidat");
                Double xCoord = NumberUtils.toDouble(record.get("X_Koordinate"));
                Double yCoord = NumberUtils.toDouble(record.get("Y_Koordinate"));

                DataStorage ds = new DataStorage(id, foreName, patronym, lastName, dateGreg, dateGregYear, dateJewish,
                        monthName, text, url, idEpidat, xCoord, yCoord);

                dataList.add(ds);
            }
        } catch (NullPointerException e) {
            e.getStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dataList;

    }
}
