/*
	SPDX-FileName: Publisher.java
	SPDX-FileCopyrightText: Copyright 2010 - 2025 Software GmbH, Darmstadt, Germany and/or its subsidiaries and/or its affiliates
	SPDX-License-Identifier: Apache-2.0
**/

package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import gui.GUI;
import test.ItalyTest;

public class Publisher {

	GUI gui;
	ItalyTest test;
	
	public Publisher(GUI gui, ItalyTest test) {
		this.gui = gui;
		this.test = test;
	}

	public Boolean publish(String topic, String content) {
		System.out.println(topic + ": " + content);
		if (test != null) {
			// JUnit
			test.receiveEvent(content);
		} else {
			Table table = gui.resultTable;
			table.getDisplay().asyncExec(new Runnable() {
  	      		public void run() {
  	      			TableItem item = new TableItem(table,SWT.NONE);
  	      			item.setText(topic + ": " + content);
  	      			table.getColumn(0).pack();
  	      		}
			});
		}
		return true;
	}
}
