/*
	SPDX-FileName: ItalyTest.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package test;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import com.espertech.esper.runtime.client.EPEventService;

import CEP.Initializer;
import CEP.SupplyChainEvent;
import CEP.TrafficEvent;


public class ItalyTest {
	
	EPEventService eventService;
	String msg;
	
	@Before
	public void setUp() throws Exception {
		Initializer i = new Initializer();
		eventService = i.initializeCep();
		i.initializePublisher(null,this);
	}

	@Test
	public void test() throws Exception {
		System.out.println("ItalyTest:test");

		// internal event
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		SupplyChainEvent sEvent = new SupplyChainEvent(timestamp,2,2,"testOrder");
		eventService.sendEventBean(sEvent,"SupplyChainEvent");

		// external event
		timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    	TrafficEvent tEvent = new TrafficEvent(timestamp,"Italy");
    	eventService.sendEventBean(tEvent,"TrafficEvent");

       	TimeUnit.SECONDS.sleep(5);
		assertTrue(msg.contains("order testOrder retarded in Italy"));
	}
	
	public void receiveEvent(String msg) {
		this.msg = msg;
	}

}
