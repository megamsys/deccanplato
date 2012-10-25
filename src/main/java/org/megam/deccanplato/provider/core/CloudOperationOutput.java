package org.megam.deccanplato.provider.core;

public class CloudOperationOutput<T extends Object> {
	
	private T out;
	private String name;
	
	public CloudOperationOutput(String tempName) {
		this.name = tempName;
	}
	
	public void set(T tempOut) {
		this.out = tempOut;
	}
	
	public T get() {
		return out;
	}

	public String name() {
		return name;
	}

}
