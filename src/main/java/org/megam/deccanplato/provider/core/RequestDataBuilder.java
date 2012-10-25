package org.megam.deccanplato.provider.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RequestDataBuilder {

	private RequestData reqData;

	public RequestDataBuilder(String jsonString) {
		System.out.println("In Constructor"+jsonString);
		parse(jsonString);
	}

	public RequestData data() {
		return reqData;
	}

	private void parse(String jsonString) {
		// parse the json string passed.
		System.out.println("Parse Method START");
		Gson gson=new Gson();
		RequestData reData=gson.fromJson(jsonString, RequestData.class);
		
		System.out.println("Parse Method"+reData.toString());
				
	}
	
}
