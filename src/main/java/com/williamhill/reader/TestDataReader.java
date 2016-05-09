package com.williamhill.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TestDataReader {

    private static final String FILE = "./databank.csv";

    /**
     * @return Test Data as List
     */
    public static List<TestData> getTestData() {
        List<TestData> testDataList = new ArrayList<TestData>();
        CSVReader reader = null;
        try {
            FileInputStream fis = new FileInputStream(new File(FILE));
            reader = new CSVReader(new InputStreamReader(fis, "UTF-8"));
            reader.readHeaders();
            while (reader.readRecord()) {
                testDataList.add(new TestData(reader.get("SPORT"), reader.get("EVENT")));
            }
        } catch (FileNotFoundException fne) {
            System.out.println("ERROR - File not found");
        } catch (IOException ioe) {
            System.out.println("ERROR - Records can't be read");
        } finally {
            if (reader != null) reader.close();
        }
        return testDataList;
    }

    public static class TestData {

        private String sport;
        private String event;

        /**
         * Sets the sport and event variables
         *
         * @param sport
         * @param event
         */
        public TestData(String sport, String event) {
            this.sport = sport;
            this.event = event;
        }

        /**
         * @return sport from data
         */
        public String getSport() {
            return sport;
        }

        /**
         * @return event from data
         */
        public String getEvent() {
            return event;
        }

        /**
         * @return formatted data string
         */
        @Override
        public String toString() {
            return "TestData{" +
                    "sport='" + sport + '\'' +
                    ", event='" + event + '\'' +
                    '}';
        }
    }

}
