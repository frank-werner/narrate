/*
	SPDX-FileName: Checker.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package newsMain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import newsMqtt.Broker;
import newsMqtt.Publisher;
import newsMqtt.local_EM_Docker;

public class Checker {
    
	static String[] array = {"krieg","unfall","streik","gewalt","offensive"};
	static ArrayList<String> keywords = new ArrayList<String>(Arrays.asList(array));
	MqttClient client;
	Publisher publisher;	
	ArrayList<String> lastKeywords = new ArrayList<String>();
	int alarmNo = 1;
			
	public void check() throws Exception {
		 
		Broker broker = new local_EM_Docker();
		client = new MqttClient(broker.getBrokerUrl(),broker.getClient(),new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		// options.setUserName(broker.getUser());
		// options.setPassword(broker.getPwd().toCharArray());
        options.setConnectionTimeout(60);
        options.setKeepAliveInterval(60);
        
		try {
			MqttConnectOptions coptions = new MqttConnectOptions();
			client.connect(coptions);
		} catch (MqttException ex) {
			System.out.println("newsMain.Checker:not connected: " + ex.getMessage());
		}


		publisher = new Publisher(client);	
		int MINUTES = 2; // The delay in minutes
		Timer timer = new Timer();
		 	timer.schedule(new TimerTask() {
		 		@Override
		 		public void run() { // Function runs every MINUTES minutes.
		 			// Run the code you want here
		 			checkNewsAPI();
		 		}
		 	}, 1000 * 60 * MINUTES, 1000 * 60 * MINUTES);
		    // 1000 milliseconds in a second * 60 per minute * the MINUTES variable.
		/*
		Weather weather = new Weather();
		weather.read();

		TomtomAPI ta = new TomtomAPI();
		Long speed = ta.read();
		System.out.println("speed = " + speed);
		*/	
	}
	
	private void checkNewsAPI() {
		System.out.println("test");
		NewsAPI na = new NewsAPI();
		ArrayList<String> keywords = na.read();
		if (keywords.isEmpty()) {
			System.out.println("kein Grund zur Sorge");
		} else {
			String keyword = keywords.get(0);
			if (lastKeywords.contains(keyword)) {
				if (keywords.size() > 1) {
					String zweitesKeyword = keywords.get(1);
					if (lastKeywords.contains(zweitesKeyword)) {
						publishAlarm(zweitesKeyword);
					} else {
						System.out.println("nix Neues");
					}
				} else {
					System.out.println("nix Anderes");
				}
			} else {
				publishAlarm(keyword);
			}
		}
	}
	
	private void publishAlarm(String keyword) {
		Boolean result = publisher.publish("mqtt/alarm",jsonErzeugen(keyword));
		if (!result) {
			System.out.println("fehler");
		} else {
			System.out.println("published: " + keyword);
			lastKeywords.add(keyword);
		}
	}
	
	private String jsonErzeugen(String keyword) {
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    	String json = "{\"timestamp\": " + "\"" + timeStamp + "\","
    			     + "\"alarmNo\": " + alarmNo + ","
				     + "\"keyword\": \"" + keyword + "\""
    				 + "}";
    	alarmNo++;
		return json;
	}

}
