package main;


import com.espertech.esper.compiler.client.EPCompileException;
import com.espertech.esper.runtime.client.EPDeployException;
import com.espertech.esper.runtime.client.EPEventService;

import CEP.Initializer;
import gui.GUI;
import gui.Widgets;

public class Main {

	public static void main(String[] args) throws EPCompileException, EPDeployException{
		
        Widgets widgets = new Widgets();
        GUI gui = new GUI(widgets);

		Initializer i = new Initializer();
		EPEventService eventService = i.initializeCep();
		i.initializePublisher(gui,null);
		
		gui.open(eventService);
	}

}
