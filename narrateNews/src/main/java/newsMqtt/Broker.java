/*
	SPDX-FileName: Broker.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package newsMqtt;

public interface Broker {

	public String getBrokerUrl();
	public String getClient();
	public String getUser();
	public String getPwd();
}
