package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Before;

import fillMain.JsonCreator;
import fillMain.SensorEntry;

public class Test {

	@Before
	public void setUp() throws Exception {
	}

	@org.junit.Test
	public void testCreateSensorEntries() {
		ArrayList<SensorEntry> list = new ArrayList<SensorEntry>();
   		for (int orderNo = 0; orderNo < 10; orderNo++) {
			for (int activityID = 0; activityID < 5; activityID++) {
				SensorEntry sensorEntry = new SensorEntry(activityID,orderNo);
				// System.out.println(sensorEntry);
				list.add(sensorEntry);
			}
		}
   		assertEquals(50,list.size());
   		for (SensorEntry entry : list) {
			if (entry.getKeyword() != null 
				&& entry.getKeyword().equals("krieg")) {
				System.out.println("krieg");
			} else {
				System.out.println("kein krieg");
			}
   		}
	}

	@org.junit.Test
	public void testCreateJson() {
		ArrayList<SensorEntry> list = new ArrayList<SensorEntry>();
		for (int activityID = 0; activityID < 5; activityID++) {
			SensorEntry sensorEntry = new SensorEntry(activityID,0);
			// System.out.println(sensorEntry);
			list.add(sensorEntry);
		}
   		assertEquals(5,list.size());
   		JsonCreator creator = new JsonCreator();
   		for (SensorEntry entry : list) {
   			String json = creator.createJson(entry);
   			assertTrue(json.contains("\"orderID\": \"testOrder0\""));
   			// System.out.println(json);
   		}
	}
	
}
