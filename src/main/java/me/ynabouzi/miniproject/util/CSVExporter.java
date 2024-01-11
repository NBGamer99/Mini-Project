package me.ynabouzi.miniproject.util;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter {
    public static void exportToCSV(String csvFilePath,  List<String[]> data) {
        try {
            File directory = new File(csvFilePath).getParentFile();
            String currentDirectory = System.getProperty("user.dir");
            System.out.println("Current working directory : " + currentDirectory);
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }

            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
                for (String[] rowData : data) {
                    writer.writeNext(rowData);
                }
                System.out.println("Data exported successfully to " + csvFilePath);
            }
        } catch (IOException e) {
            System.out.println("Error while exporting data to " + csvFilePath);
        }
    }
}