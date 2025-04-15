/*
	SPDX-FileName: MQTT_Client.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package newsMqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class MQTT_Client {

	Broker broker = new local_EM_Docker();
	public MqttClient client;
	
	public MQTT_Client() throws MqttException {
		client = new MqttClient(broker.getBrokerUrl(),broker.getClient(),new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		// options.setUserName(broker.getUser());
		// options.setPassword(broker.getPwd().toCharArray());
		options.setConnectionTimeout(60);
		options.setKeepAliveInterval(60);
	}
	
	public void connect() throws MqttException {
		client.connect();
	}
}
