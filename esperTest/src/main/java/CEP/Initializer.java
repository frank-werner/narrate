package CEP;

import java.util.ArrayList;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompileException;
import com.espertech.esper.compiler.client.EPCompiler;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.EPDeployException;
import com.espertech.esper.runtime.client.EPDeployment;
import com.espertech.esper.runtime.client.EPDeploymentService;
import com.espertech.esper.runtime.client.EPEventService;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPRuntimeProvider;
import com.espertech.esper.runtime.client.EPStatement;

import gui.GUI;
import main.Publisher;
import main.Queries;
import main.Query;
import test.ItalyTest;


public class Initializer {

	Publisher publisher;
	ArrayList<EPDeployment> deployments = new ArrayList<EPDeployment>();

	public EPEventService initializeCep() throws EPCompileException, EPDeployException {

		// CEP
		Configuration configuration = new Configuration();
		configuration.getCommon().addEventType(SupplyChainEvent.class);
		configuration.getCommon().addEventType(TrafficEvent.class);

		Queries queries = new Queries();

		// https://sungsoo.github.io/2014/01/02/overview-of-esper.html
		// https://www.espertech.com/2018/10/23/esper-8-migrating-api-uses-from-older-versions/
		EPRuntime runtime = EPRuntimeProvider.getDefaultRuntime(configuration);
		EPCompiler compiler = EPCompilerProvider.getCompiler();
		EPDeploymentService deploymentService = runtime.getDeploymentService();
		EPEventService eventService = runtime.getEventService();
		EPStatement statement = null;
		for (Query query : queries.queryList) {
			String queryInclName = "@name('" + query.getName() + "')" + query.getBody();
			EPCompiled epCompiled = compiler.compile(queryInclName,new CompilerArguments(configuration));
			EPDeployment deployment = deploymentService.deploy(epCompiled);
			deployments.add(deployment);
			statement = deploymentService.getStatement(deployment.getDeploymentId(),query.getName());
		}
		
		statement = deploymentService.getStatement(deployments.get(0).getDeploymentId(),"narrate-statement1");
		statement.addListener((newData, oldData, l_statement, l_runtime) -> {
			String timestamp = (String) newData[0].get("timestamp");
			int activityID = (int) newData[0].get("activityID");
			int supplierID = (int) newData[0].get("supplierID");
			String orderID = (String) newData[0].get("orderID");
			System.out.println(String.format("Main1: Timestamp = %s, ActivityID = %d, SupplierID = %d, OrderID = %s",
					timestamp, activityID, supplierID, orderID));
		});

		// ordered -> packed
		statement = deploymentService.getStatement(deployments.get(1).getDeploymentId(),"narrate-statement2");
		statement.addListener((newData, oldData, l_statement, l_runtime) -> {
			alertDelay(newData[0]);
		});

		// packed -> loaded
		statement = deploymentService.getStatement(deployments.get(2).getDeploymentId(),"narrate-statement3");
		statement.addListener((newData, oldData, l_statement, l_runtime) -> {
			alertDelay(newData[0]);
		});

		// loaded -> arrived
		statement = deploymentService.getStatement(deployments.get(3).getDeploymentId(),"narrate-statement4");
		statement.addListener((newData, oldData, l_statement, l_runtime) -> {
			alertDelay(newData[0]);
		});
		
		// arrived -> unpacked
		statement = deploymentService.getStatement(deployments.get(4).getDeploymentId(),"narrate-statement5");
		statement.addListener((newData, oldData, l_statement, l_runtime) -> {
			alertDelay(newData[0]);
		});
		
		// road for supplier no2 blocked in Italy
		statement = deploymentService.getStatement(deployments.get(5).getDeploymentId(),"narrate-statement6");
		statement.addListener((newData, oldData, l_statement, l_runtime) -> {
			alertTraffic(newData[0]);
		});
		
		// road for supplier no2 blocked in Italy
		statement = deploymentService.getStatement(deployments.get(6).getDeploymentId(),"narrate-statement7");
		statement.addListener((newData, oldData, l_statement, l_runtime) -> {
			alertTraffic(newData[0]);
		});
		
		return eventService;
	}

	public void initializePublisher(GUI gui, ItalyTest test) {
		publisher = new Publisher(gui,test);
	}

	private void alertDelay(EventBean data) {
		String timestamp = (String) data.get("timestamp");
		int activityID = (int) data.get("activityID");
		int supplierID = (int) data.get("supplierID");
		String orderID = (String) data.get("orderID");
		System.out.println(String.format("alertDelay: Timestamp = %s, ActivityID = %d, SupplierID = %d, OrderID = %s",
				timestamp, activityID, supplierID, orderID));
		String msg = "at " + timestamp + " activity " + activityID + " not pursued in time";
		publisher.publish("mqtt/delay", msg);
	}

	private void alertTraffic(EventBean data) {
		String timestamp = (String) data.get("timestamp");
		int activityID = (int) data.get("activityID");
		int supplierID = (int) data.get("supplierID");
		String orderID = (String) data.get("orderID");
		String location = (String) data.get("b.location");
		System.out.println(String.format("alertTraffic: Timestamp = %s, ActivityID = %d, SupplierID = %d, OrderID = %s, location = %s",
				timestamp, activityID, supplierID, orderID, location));
		String msg = "at " + timestamp + " order " + orderID + " retarded in " + location;
		publisher.publish("mqtt/traffic", msg);
	}
}
