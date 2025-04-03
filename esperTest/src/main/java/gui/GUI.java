package gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.espertech.esper.runtime.client.EPEventService;

import CEP.AbstractEvent;
import CEP.SupplyChainEvent;
import CEP.TrafficEvent;
import main.Queries;
import main.Query;


public class GUI {

	private Widgets widgets;
	EPEventService service;
	
	protected Display display;
	public Shell shell;

	Button internalButton, externalButton;
	ArrayList<Button> activityButtons = new ArrayList<Button>(4),
			          supplierButtons = new ArrayList<Button>(3);
	String[] activities = {"ordered","packed","loaded","arrived","unpacked"}; 
	public Text feld, msg;
	int activityID = 0, supplierID = 0;
	Text orderIDText;
	Table queryTable, eventTable;
	public Table resultTable;
	String location;

	
	public GUI(Widgets widgets) {
		this.widgets = widgets;
	}

	public void open(EPEventService service) {
		
		this.display = widgets.display;
		this.shell = widgets.shell;
		this.service = service;

		shell.open();
		shell.setText("ESPER");

        shell.setLayout(new GridLayout());	

        widgets.titelErzeugen(shell,"ESPER Handler for NARRATE");
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        shell.setLayout(gridLayout);
        
        Composite leftComposite = widgets.createComposite(shell);
        Composite buttonComposite = widgets.createComposite(shell);        

        queryTable = widgets.createTable(leftComposite,2,700,200);
        fillTable();
        eventTable = widgets.createTable(leftComposite,1,700,200);
        resultTable = widgets.createTable(leftComposite,1,700,200);

        Group activityGroup = widgets.createRadioGroup("Activity",buttonComposite);
        for (int i = 0; i < 5; i++) {
        	Button activityButton = new Button(activityGroup, SWT.RADIO);
        	activityButton.setText(activities[i]);
        	if (i == 0) activityButton.setFocus();
        	activityButtons.add(activityButton);
        	activityButton.addSelectionListener(new SelectionAdapter() {
        		public void widgetSelected(SelectionEvent e) {
        			activityID = activityButtons.indexOf((Button) e.widget);
        		}
        	});
        }
        
        Group supplierGroup = widgets.createRadioGroup("Supplier",buttonComposite);
        for (int i = 0; i < 3; i++) {
        	Button supplierButton = new Button(supplierGroup, SWT.RADIO);
        	supplierButton.setText("Supplier" + (i + 1));
        	if (i == 0) supplierButton.setFocus();
        	supplierButtons.add(supplierButton);
        	supplierButton.addSelectionListener(new SelectionAdapter() {
        		public void widgetSelected(SelectionEvent e) {
        			supplierID = supplierButtons.indexOf((Button) e.widget);
        		}
        	});
        }

        Group deliveryGroup = widgets.orderIDGroupErzeugen(buttonComposite);
        orderIDText = widgets.textErzeugen(deliveryGroup);

       	internalButton = widgets.buttonErzeugen(buttonComposite,"Internal Event");
       	internalButton.addSelectionListener(new SelectionAdapter() {
     		public void widgetSelected(SelectionEvent e) {
        		String orderID = orderIDText.getText();
        		if (orderID.isEmpty()) {
    				msg.setText("OrderID darf nicht leer sein");
        		} else {
        			String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        			SupplyChainEvent event = new SupplyChainEvent(timestamp,activityID,supplierID,orderID);
        			triggerEvent(event,"SupplyChainEvent");
        		}
      			msg.pack();
       		}
       	});       	

        Group locationGroup = widgets.createRadioGroup("Location",buttonComposite);
    	Button locationItalyButton = new Button(locationGroup, SWT.RADIO);
    	locationItalyButton.setText("Italy");
    	locationItalyButton.addSelectionListener(new SelectionAdapter() {
    		public void widgetSelected(SelectionEvent e) {
    			location = "Italy";
    		}
    	});
    	locationItalyButton.setFocus();
    	Button locationSpainButton = new Button(locationGroup, SWT.RADIO);
    	locationSpainButton.setText("Spain");
    	locationSpainButton.addSelectionListener(new SelectionAdapter() {
    		public void widgetSelected(SelectionEvent e) {
    			location = "Spain";
    		}
    	});

        externalButton = widgets.buttonErzeugen(buttonComposite,"External Event");
        externalButton.addSelectionListener(new SelectionAdapter() {
        	public void widgetSelected(SelectionEvent e) {
        		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            	TrafficEvent event = new TrafficEvent(timestamp,location);
               	triggerEvent(event,"TrafficEvent");
              	msg.pack();
       		}
       	});

        msg = widgets.messageErzeugen(shell);
	    msg.pack();

	    shell.pack();
        shell.layout();
        
		while (!shell.isDisposed()) { 
		    if (!display.readAndDispatch()) { 
	    	}
	    	display.sleep();
	    }
	};

	private void triggerEvent(AbstractEvent event, String eventClass) {
		service.sendEventBean(event,eventClass);
		msg.setText("Triggered Event: " + event);
		TableItem item = new TableItem(eventTable,SWT.NONE);
		item.setText(event.toString());
   		eventTable.getColumn(0).pack();
	}
	
	private void fillTable() {
		Queries queries = new Queries();
		for (Query query : queries.queryList) {
			TableItem item = new TableItem(queryTable,SWT.NONE);
			item.setText(query.getArray());
		}
		queryTable.getColumn(0).pack();
		queryTable.getColumn(1).pack();
	}
	
}
