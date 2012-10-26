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
