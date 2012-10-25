package org.megam.deccanplato.provider;

import java.util.Formatter;

import org.megam.deccanplato.provider.core.AdapterAccess;

public class Provider {

	private String name;
	private String description;
	private ProviderAdapter adapter;
	private AdapterAccess access;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProviderAdapter getAdapter() {
		return adapter;
	}

	public AdapterAccess getAdapterAccess() {
		return access;
	}

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n",
				"*----------------------------------------------*");
		formatter.format("%12s = %s%n", "name", getName());
		formatter.format("%12s = %s%n", "description", getDescription());
		formatter.format("%12s = %s%n", "adapter", getAdapter());
		formatter.format("%12s = %s%n", "adap_access", getAdapterAccess());
		formatter.format("%s%n",
				"*----------------------------------------------*");
		formatter.close();
		return strbd.toString();
	}
}
