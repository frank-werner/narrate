/*
	SPDX-FileName: TomtomAPI.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package newsMain;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class TomtomAPI {

    static StringBuffer url = new StringBuffer("https://api.tomtom.com/traffic/services/4/flowSegmentData/absolute/10/json");
    static boolean first = true;
    
	public Long read() throws Exception {
		
		addParm("key","ustBsQB5G9ssPdUxwRoRA2DOTEvZ1Dzt&point=49.825702,8.647984");
		addParm("point","49.825702,8.647984");
		
		HttpReader reader = new HttpReader();
		JSONObject json = reader.read(url.toString());
		JSONObject flowSegmentData = (JSONObject) json.get("flowSegmentData");        
		Long speed = (Long) flowSegmentData.get("currentSpeed");        
		return speed;
	}
	
	private static void addParm(String name,String value) {
		if (first) {
			url.append("?" + name);
			first = false;
		} else 
			url.append("&" + name);
		if (value != null)
			url.append("=" + value);
	}

}
