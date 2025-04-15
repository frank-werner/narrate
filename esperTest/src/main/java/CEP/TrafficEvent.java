/*
	SPDX-FileName: TrafficEvent.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package CEP;

public class TrafficEvent extends AbstractEvent {

  private String location;

  public TrafficEvent(String timestamp, String location) {
	  this.timestamp = timestamp;
	  this.location = location; 
  }

  public String getTimestamp() {
	  return timestamp;
  }

  public String getLocation() {
	  return location;
  }
  
  public String toString() {
	  return "TrafficEvent: " + timestamp + " " + location;
  }
}
