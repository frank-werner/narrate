package main;

import java.util.ArrayList;

public class Queries {

	public ArrayList<Query> queryList = new ArrayList<Query>();
	
	public Queries() {
		queryList.add(new Query("narrate-statement1","select * from SupplyChainEvent"));
		queryList.add(new Query("narrate-statement2",
				"select a.* from pattern [every a=SupplyChainEvent(activityID=0)" 
			  + " -> (timer:interval(5 sec) " 
			  + "    and not SupplyChainEvent(supplierID=a.supplierID and orderID=a.orderID" 
			  + "                         and activityID=1))]"));
		queryList.add(new Query("narrate-statement3",
				"select a.* from pattern [every a=SupplyChainEvent(activityID=1)" 
			  + " -> (timer:interval(5 sec) " 
			  + "    and not SupplyChainEvent(supplierID=a.supplierID and orderID=a.orderID" 
			  + "                         and activityID=2))]"));
		queryList.add(new Query("narrate-statement4",
				"select a.* from pattern [every a=SupplyChainEvent(activityID=2)" 
			  + " -> (timer:interval(10 sec) " 
			  + "    and not SupplyChainEvent(supplierID=a.supplierID and orderID=a.orderID" 
			  + "                         and activityID=3))]"));
		queryList.add(new Query("narrate-statement5",
				"select a.* from pattern [every a=SupplyChainEvent(activityID=3)" 
			  + " -> (timer:interval(5 sec) " 
			  + "    and not SupplyChainEvent(supplierID=a.supplierID and orderID=a.orderID" 
			  + "                         and activityID=4))]"));
		/*
		queryList.add(new Query("narrate-statement6",		
				"select a.*,b.location from pattern [every a=SupplyChainEvent(activityID=1 and supplierID=2)"
			  + " -> (timer:interval(4 seconds) "
			  + "    and b=TrafficEvent(location=\"Italy\"))]"));
		*/
		queryList.add(new Query("narrate-statement6",		
				"select a.*,b.location from pattern [every a=SupplyChainEvent(activityID=2 and supplierID=2)"
			  + " -> b=TrafficEvent(location=\"Italy\")"
			  + "    and not timer:interval(4 seconds)]"));
		queryList.add(new Query("narrate-statement7",		
				"select a.*,b.location from pattern [every b=TrafficEvent(location=\"Italy\")"
			  + " -> a=SupplyChainEvent(activityID=2 and supplierID=2)"
			  + "    and not timer:interval(4 seconds)]"));
	}
	
	public Query get(int i) {
		return queryList.get(i);
	}
}
