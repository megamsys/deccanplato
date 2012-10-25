package org.megam.deccanplato.provider.core;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import org.megam.core.api.secure.AccessToken;

public class AccessInfo implements DataMap {

	private Map<String, String> access = new HashMap<String, String>();
	private AccessToken token;

	public AccessInfo() {
		
	}

	public Map<String, String> asMap() {
		return access;
	}

	public AccessToken token() {
		return token;
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
