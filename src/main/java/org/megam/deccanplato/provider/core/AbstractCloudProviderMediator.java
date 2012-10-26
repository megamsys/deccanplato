/* “Copyright 2012 Megam Systems”
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
