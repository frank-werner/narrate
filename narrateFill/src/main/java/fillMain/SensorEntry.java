/*
	SPDX-FileName: SensorEntry.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package fillMain;

public class SensorEntry {
	
	int activityID, supplierID;
	String orderID;
	Five five = null;
	
	public SensorEntry(int activityID, int orderNo) {
		this.activityID = activityID;
		this.orderID = "testOrder" + orderNo;
		this.supplierID = 0;
		if (activityID == 0) {
			createFive();
		}
	}
	
	public String toString() {
		return activityID + " " + orderID;
	}
	
	private void createFive() {
		this.five = new Five();
	}
	
	public String getKeyword() {
		if (five == null)
			return null;
		else return five.keyword;
	}
}
