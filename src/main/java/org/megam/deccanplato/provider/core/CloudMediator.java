package org.megam.deccanplato.provider.core;

public interface CloudMediator {
	
	
	public SendBackResponse handleRequest();

	public void registerCloudBridgeListener(
			CloudBridgeMediationListener listener);
	

}
