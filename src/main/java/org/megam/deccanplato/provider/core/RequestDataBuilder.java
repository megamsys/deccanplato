package org.megam.deccanplato.provider.core;

import java.util.Formatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RequestDataBuilder {

	private RequestData reqData;

	public RequestDataBuilder(String jsonString) {
		parse(jsonString);
	}
	
	private void parse(String jsonString) {
		// parse the json string passed.
		Gson gson=new Gson();
		reqData = gson.fromJson(jsonString, RequestData.class);				
	}

	public RequestData data() {
		return reqData;
	}
	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n",
				"*----------------------------------------------*");
		formatter.format("%12s = %s%n", "reqdata", data());
		formatter.format("%s%n",
				"*----------------------------------------------*");
		formatter.close();
		return strbd.toString();

	}

	
	
}
