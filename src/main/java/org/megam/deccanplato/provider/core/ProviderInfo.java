package org.megam.deccanplato.provider.core;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class ProviderInfo implements DataMap {

	private Map<String, String> business_activity = new HashMap<String, String>();
	private Map<String, String> access = new HashMap<String, String>();

	
	private static final String PROVIDER = "provider";


	public ProviderInfo() {	
		
	}


	public Map<String, String> map() {
		if(!business_activity.keySet().containsAll(access.keySet())) {
			business_activity.putAll(access);
		}
		return business_activity;
	}
	
	public String getProviderName() {
		return map().get(PROVIDER);
	}
	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, String> entry : map().entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		return strbd.toString();
	}

	

}
