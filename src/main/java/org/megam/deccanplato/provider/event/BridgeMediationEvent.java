package org.megam.deccanplato.provider.event;

public class BridgeMediationEvent<E extends Object> {
	
	private E someObject;
	
	public BridgeMediationEvent(E tempPassedObj) {
		this.someObject = tempPassedObj;
	}
	
	public  E get() {
		return someObject;		
	}
	
	public String toString() {
		return null;
	}

}
