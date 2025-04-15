/*
	SPDX-FileName: Five.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package fillMain;

import java.util.Random;

public class Five {

	String[] cloudArray = {"---","wolkenlos","bew�lkt"};
	String[] keywordArray = {"---","krieg","gewalt"};
	long speed, temperature;
	String clouds, serious, keyword;
	
	Five() {
		this.speed = generateRandomLong(5L,25L);
		this.temperature = generateRandomLong(-5L,30L);
		this.clouds = cloudArray[generateRandomInt(0,2)];
		this.serious = "---";
		this.keyword = keywordArray[generateRandomInt(0,2)];
	}
	
	private Long generateRandomLong(long leftLimit, long rightLimit) {
	    return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	}
	
	public int generateRandomInt(int leftLimit, int rightLimit) {
	    return leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
	}
}
