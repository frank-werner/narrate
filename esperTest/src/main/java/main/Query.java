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
