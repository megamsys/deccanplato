package org.megam.deccanplato.provider.core;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class ResponseData<T extends Object> implements SendBackResponse<T> {
	
	private Map<String, T> responseMap = new HashMap<String, T>();
	
	public ResponseData() {
	}
		
	public Map<String, T> map() {
		return responseMap;
	}

	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, T> entry : map().entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		return strbd.toString();
	}


	public void put(String name, T singleObj) {
		responseMap.put(name, singleObj);
	}
	

}
