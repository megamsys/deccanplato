package org.megam.deccanplato.provider.core;

public interface CloudMediator {
	
	
	public<T extends Object> SendBackResponse<T> handleRequest();

	public void registerCloudBridgeListener(
			CloudBridgeMediationListener listener);
	

}
