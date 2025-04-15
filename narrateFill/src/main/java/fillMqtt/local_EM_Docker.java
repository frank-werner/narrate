/*
	SPDX-FileName: local_EM_Docker.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package fillMqtt;

public class local_EM_Docker implements Broker {

	private String broker = "tcp://localhost:1883";
	// TLS/SSL
	// String broker = "ssl://broker.emqx.io:8883";
	private String username = "emqx";
	private String password = "public";

	public String getBrokerUrl() {
		return broker;
	}
	public String getUser() {
		return username;
	}
	public String getPwd() {
		return password;
	}
	public String getClient() {
		return "fill_client";
	}
}
