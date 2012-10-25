package org.megam.deccanplato.provider.core;

import org.megam.deccanplato.provider.event.BridgeMediationEvent;
import org.megam.deccanplato.provider.event.BridgeMediationStatusEvent;

public interface CloudBridgeMediationListener {
	
	public<B extends BridgeMediationEvent> void bridgeEvent(B evt);
	

}
