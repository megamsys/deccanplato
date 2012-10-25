package org.megam.deccanplato.provider.core;

import java.util.Formatter;



public class RequestData {
	
	private AccessInfo access;
	private GeneralInfo general;
	private OutputInfo output;

	
	public RequestData() {		
	}
	
	public AccessInfo getAccess() {
		return access;
	}
	
	public GeneralInfo getGeneral() {
		return general;
	}
	
	public OutputInfo getOutput() {
		return output;
	}
	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n", getAccess().toString());
		formatter.format("%s%n", getGeneral().toString());
		formatter.format("%s%n", getOutput().toString());
		return strbd.toString();
	}
	
}
