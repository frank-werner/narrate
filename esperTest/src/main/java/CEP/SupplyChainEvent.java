/*
	SPDX-FileName: SupplyChainEvent.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package CEP;

public class SupplyChainEvent extends AbstractEvent {

  private int activityID;
  private int supplierID;
  private String orderID;

  public SupplyChainEvent(String timestamp, int activityID, int supplierID, String orderID) {
    this.timestamp = timestamp;
    this.activityID = activityID;
    this.supplierID = supplierID;
    this.orderID = orderID; 
  }

  public String getTimestamp() {
	  	return timestamp;
  }

  public int getActivityID() {
	   return activityID;
  }
  
  public int getSupplierID() {
	   return supplierID;
  }

  public String getOrderID() {
	   return orderID;
  }
  
  public String toString() {
	  return "SupplyChainEvent: " + timestamp + " " + activityID + " " + supplierID + " " + orderID;
  }

}
