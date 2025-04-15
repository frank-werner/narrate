/*
	SPDX-FileName: Query.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package main;

public class Query {

	String name;
	String body;
	
	Query(String name,String body) {
		this.name = name;
		this.body = body;
	}
	
	public String getName() {
		return name;
	}
	public String getBody() {
		return body;
	}
	public String[] getArray() {
		return new String[]{name,body};
	}
}
