package org.megam.deccanplato.provider.core;

import java.util.HashSet;
import java.util.Set;

import org.megam.deccanplato.provider.event.BridgeMediationEvent;
import org.megam.deccanplato.provider.event.BridgeMediationStatusEvent;

public abstract class AbstractCloudProviderMediator implements CloudMediator, Saveable {

	private Saveable saveable;
	
	private Set<CloudBridgeMediationListener> listeners = new HashSet<>();
	
	public AbstractCloudProviderMediator() {	
		registerCloudBridgeListener(new TempMediationListener());
	}
	
		
	public void registerCloudBridgeListener(CloudBridgeMediationListener listener) {
		listeners.add(listener);			
	}
	
	public <T extends BridgeMediationEvent> void fireEvent(T evt) {
		for(CloudBridgeMediationListener listener : listeners) {
			listener.bridgeEvent(evt);
		}
	}


	public void setSaveableToUse(Saveable tempSaveable) {
		this.saveable  = tempSaveable;
	}
	
	public boolean save() {
		if(saveable !=null) {
			return saveable.save();
		}
		return false;
	}
	
	class TempMediationListener implements  CloudBridgeMediationListener  {

	@Override
	public void bridgeEvent(BridgeMediationEvent evt) {
		// TODO Auto-generated method stub
		
	}

	
	 
 }
	
}
