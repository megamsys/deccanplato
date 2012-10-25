package org.megam.deccanplato.provider.core;

import org.megam.deccanplato.provider.event.BridgeMediationEvent;

public abstract class AbstractCloudOperation implements
		CloudOperation, CloudBridgeMediationListener {

	private CloudMediator parent;
	
	public enum Status {
        STARTED,
		RUNNING,
        COMPLETED,
        FAILED,
        SUCCESSFUL
    }
	
	public AbstractCloudOperation(CloudMediator tempParent) {
		this.parent = tempParent;
		parent.registerCloudBridgeListener(this);
	}

	public CloudMediator getParent() {
		return parent;
	}
	
	public <B extends BridgeMediationEvent> void bridgeEvent(B evt) {
		/** 
		 ***/ 

	}

}
