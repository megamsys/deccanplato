package org.megam.deccanplato.provider.core;

import org.megam.deccanplato.provider.ProviderAdapter;

public class AdapterWrapper {
	
	public enum Status {
        STARTED,
		RUNNING,
        COMPLETED,
        FAILED,
        SUCCESSFUL
    }
	
	private ProviderAdapter adapter;
	
	private Status stat;
	
	public AdapterWrapper(ProviderAdapter providerAdapter) {
		stat = Status.STARTED;
		this.adapter = providerAdapter;
	}
	
	public void preRun() {
		/** setup adapter specific data format **/
		adapter.build();
		
	}
	
	public void run() {
		stat = Status.RUNNING;
		
	}
	
	public Status status() {
		return stat;
	}
	
	
	public void postRun() {
		if(stat == Status.SUCCESSFUL) {
			
			
		}
		stat = Status.COMPLETED;

	}

}
