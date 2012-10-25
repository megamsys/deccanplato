package org.megam.deccanplato.provider.core;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class OutputInfo implements DataMap {
	
private Map<String,String> outputMap = new HashMap<String,String> ();
	
	public OutputInfo(String jsonOutputString) {
		parse(jsonOutputString);
	}
	
	public Map<String,String> asMap() {
		return outputMap;
	}
	
	
	private void parse(String jsonOutputString) {
		
	}
	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, String> entry : asMap().entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		return strbd.toString();
	}

}
