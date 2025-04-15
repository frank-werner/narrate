/*
	SPDX-FileName: JsonCreator.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package fillMain;

public class JsonCreator {
	
	public String createJson(SensorEntry sensorEntry) {
    	String json = "{\"activityID\": " + sensorEntry.activityID + ","
			     	 + "\"supplierID\": " + sensorEntry.supplierID + ","
				     + "\"orderID\": \"" + sensorEntry.orderID + "\""
				     + extra(sensorEntry.activityID,sensorEntry.five)
    				 + "}";
		return json;
	}
	
	private String extra(int activityID,Five five) {
		if (activityID == 0) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(",\"speed\": " + five.speed);
			buffer.append(",\"temperature\": " + five.temperature);
			buffer.append(",\"clouds\": \"" + five.clouds + "\"");
			buffer.append(",\"serious\": \"" + five.serious + "\"");
			buffer.append(",\"keyword\": \"" + five.keyword + "\"");
			return buffer.toString();
		} else {
			return "";
		}
	}

}
