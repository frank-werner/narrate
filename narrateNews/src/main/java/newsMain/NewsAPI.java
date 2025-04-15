/*
	SPDX-FileName: NewsAPI.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package newsMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class NewsAPI {

    static StringBuffer url = new StringBuffer("https://newsapi.org/v2/top-headlines");
    static boolean first = true;
	static String[] array = {"krieg","unfall","streik","gewalt","offensive"};
	static ArrayList<String> keywords = new ArrayList<String>(Arrays.asList(array));
    
	public ArrayList<String> read() {
		
		// addParm("G",null);
		addParm("apiKey","6392ab5cf1a640958bc9c43a664bcb45");
		addParm("country","de");
		
		JSONObject json = null;
		HttpReader reader = new HttpReader();
		try {
			json = reader.read(url.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		String status = (String) json.get("status");
		System.out.println("status = " + status);
		
		if (status.equals("error")) {
			System.out.println(json.get("message"));
			return null;
		} else {
			JSONArray response =  (JSONArray) json.get("articles");
			ArrayList<String> titles = new ArrayList<String>();
			Iterator<?> responseIterator = response.iterator();
			while(responseIterator.hasNext()) {	
				JSONObject article = (JSONObject) responseIterator.next();
				System.out.println(article);
				String title = (String) article.get("title");
				titles.add(title);
			}
			return getKeywords(titles);
		}
	}
	
	private ArrayList<String> getKeywords(ArrayList<String> news) {
		ArrayList<String> result = new ArrayList<String>();
		for (String msg : news) {
			for (String keyword : keywords) {
				if (msg.toLowerCase().contains(keyword)) {
					result.add(keyword);
					break;
				}
			}
		}
		return result;
	}

	private  void addParm(String name,String value) {
		if (first) {
			url.append("?" + name);
			first = false;
		} else 
			url.append("&" + name);
		if (value != null)
			url.append("=" + value);
	}

}
