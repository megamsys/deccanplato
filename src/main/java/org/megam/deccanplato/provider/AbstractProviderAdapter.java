package org.megam.deccanplato.provider;

import java.util.Formatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractProviderAdapter {
		
	@Autowired
	protected ProviderRegistry registry;
	
	protected Map<String,String> args;

	protected String cloud_app;

	protected String business_function;
	
	
	public AbstractProviderAdapter(Map<String,String> tempArgs) {
		this.args = tempArgs;	
		configure();
	}
	
	public abstract void configure();
	
	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, String> oneArg : args.entrySet()) {
			formatter.format("%10s = %s%n", oneArg.getKey(), oneArg.getValue());
		}
		formatter.close();
		return strbd.toString();
	}

}
