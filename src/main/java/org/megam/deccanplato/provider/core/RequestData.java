package org.megam.deccanplato.provider.core;

import java.util.Formatter;

import com.google.gson.annotations.SerializedName;



public class RequestData {
	
	private AccessInfo system;
	private ProviderInfo provider;	
	private OutputInfo execution;

	
	public RequestData() {
	}
	
	public AccessInfo getAccess() {
		return system;
	}
	
	public ProviderInfo getGeneral() {
		return provider;
	}
	
	public OutputInfo getOutput() {
		return execution;
	}
	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n", getAccess().toString());
		formatter.format("%s%n", getGeneral().toString());
		formatter.format("%s%n", getOutput().toString());
		formatter.close();
		return strbd.toString();
	}
	
}
