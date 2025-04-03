package gui;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;


public class Widgets {

	public Display display;
	public Shell shell;
	public Color green, white;
	
	public Widgets() {
		display = new Display();
		
	    green = new Color(display,100,200,100);
	    white = new Color(display,250,250,250);  
	    
	    shell = new Shell(display);
	    Image icon = getIcon("esper.ico");
	    shell.setImage(icon);
	}
	
	
	public Table createTable(Composite composite, int cols, int width, int höhe) {
		final Table table = new Table(composite, SWT.MULTI);
		for (int i = 0; i < cols; i++ ) {
			TableColumn col = new TableColumn(table,SWT.NULL);
			col.pack();
		}
	    GridData gridData = new GridData();
	    gridData.widthHint = width;
	    gridData.heightHint = höhe;
	    table.setLayoutData(gridData);
	    return table;
	}

	public Composite createComposite(Composite outer) {
	    Composite inner = new Composite(outer,SWT.BORDER);
		GridData data = new GridData();
		data.verticalSpan = 2;
		inner.setLayoutData(data);
 	    inner.setLayout(new GridLayout());
	    return inner;
	}
		
	public Group createRadioGroup(String text,Composite composite) {	
        Group radioGroup = new Group(composite, SWT.SHADOW_IN);
        radioGroup.setText(text + ": ");
        radioGroup.setLayout(new RowLayout(SWT.VERTICAL));
	    return radioGroup;
	}
	
	public Group orderIDGroupErzeugen(Composite composite) {	
        Group orderIDGroup = new Group(composite, SWT.SHADOW_IN);
        orderIDGroup.setText("OrderID:");
        orderIDGroup.setLayout(new GridLayout());
	    return orderIDGroup;
	}
	
	public Button buttonErzeugen(Composite composite,String text) {
	    Button button = new Button(composite,SWT.PUSH);
	    button.setText(text);
	    GridData gridData = new GridData();
	    gridData.heightHint = 60;
	    gridData.widthHint = 200;
	    button.setLayoutData(gridData);
	    fontVergroessern(button,12);
	    return button;
	}
	
	
	public void fontVergroessern(Control control,int groesse) {
		FontData[] fD = control.getFont().getFontData();
		fD[0].setHeight(groesse);
		Font font = new Font(display,fD);
		control.setFont(font);
	}
	
	public Text titelErzeugen(Composite composite,String text) {
	    Text titel = new Text(composite,SWT.NULL);
	    titel.setText(text);
	    fontVergroessern(titel,30);
        titel.setBackground(green);
        titel.setForeground(white);
        titel.setEnabled(false);
	    GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 2;
	    titel.setLayoutData(gridData);	
	    return titel;
	}
	
	public Text labelErzeugen(Composite composite,String text, int size) {
	    Text label = new Text(composite,SWT.NULL);
	    label.setText(text);
	    label.setEnabled(false);
	    fontVergroessern(label,size);
	    return label;
	}
	
	public Text messageErzeugen(Composite composite) {
	    Text msg = new Text(composite,SWT.NULL);
	    msg.setEnabled(false);
	    fontVergroessern(msg,12);
	    return msg;
	}
	
	public Text textErzeugen(Composite composite) {
	    Text text = new Text(composite,SWT.NULL);
	    fontVergroessern(text,12);
	    GridData gridData = new GridData();
	    gridData.widthHint = 100;
	    text.setLayoutData(gridData);	
	    return text;
	}
	
	
	public Image getIcon(String name) {
		String path = "resources/" + name;
		InputStream input = Widgets.class.getClassLoader().getResourceAsStream(path);
		Image icon = new Image(display,input);
		return icon;
	}
}
