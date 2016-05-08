package com.williamhill;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import com.williamhill.reader.CSVReader;
import junit.framework.TestCase;
import com.williamhill.reader.TestDataReader.TestData;
import org.junit.Assert;

public class CSVReaderTest extends TestCase {

	private TestData[] testDataList = new TestData[] {
			new TestData("Football", "Spain   v   Italy"),
			new TestData("Cricket", "West Indies   v   Sri Lanka"),
			new TestData("Football", "Egypt U20   v   England U20")
	};

	public void testReadFromDatabank() throws Exception {

		FileInputStream fis = new FileInputStream(new File("./databank.csv"));
		CSVReader reader = new CSVReader(new InputStreamReader(fis, "UTF-8"));
		Assert.assertTrue("No headers present, verification failed", reader.readHeaders());
		int counter = 0;
		while (reader.readRecord()) {
			String sport = reader.get("SPORT");
			String event = reader.get("EVENT");
			Assert.assertEquals(testDataList[counter].getSport(),sport);
			Assert.assertEquals(testDataList[counter].getEvent(), event);
			counter++;
		}
		reader.close();
	}

}
