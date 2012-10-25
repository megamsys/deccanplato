package org.megam.deccanplato.provider;

public interface ProviderAdapter<T extends Object> {
	
	public T handle();
	
	public boolean build();
	

}
