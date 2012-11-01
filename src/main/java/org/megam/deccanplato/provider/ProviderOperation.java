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
package org.megam.deccanplato.provider;

import java.util.Map;
import java.util.Map.Entry;

import org.megam.deccanplato.provider.core.AbstractCloudOperation;
import org.megam.deccanplato.provider.core.CloudMediator;
import org.megam.deccanplato.provider.core.CloudOperationException;
import org.megam.deccanplato.provider.core.CloudOperationOutput;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.MultiDataMap;
import org.megam.deccanplato.provider.core.ProviderInfo;
import org.megam.deccanplato.provider.event.BridgeMediationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * A cloud operation class with performs specific tasks as appropriate for a provider. Some of the activities
 * performed are setting up the correct provider, provider adapter, provider adapter access.
 * When the operation is run, the pre-operation runs the adapter access. The output is captured in a 
 * cloudoperationoutput and passed back to the mediator.
 * @author ram
 *
 */
public class ProviderOperation extends AbstractCloudOperation {

	@Autowired
	private ProviderRegistry registry;

	private ProviderInfo info;
	private Provider prov;

	/**
	 * 
	 * @param tempInfo
	 * @param tempParent
	 */
	public ProviderOperation(ProviderInfo tempInfo, CloudMediator tempParent) {
		super(tempParent);
		this.info = tempInfo;
	}

	public boolean isFitToRun() {
		if (prov != null && prov.getAccess() != null) {
			return prov.getAccess().isSuccessful();
		}

		return false;
	}

	public <V extends Object> void preOperation() throws CloudOperationException {
		System.out.println("REGISTRY IN PROVIDER OPERATION::::::::::::"+registry);
		if(registry==null) {
			System.out.println("REGISTER :>>>>>:"+registry.instance());
			registry=ProviderRegistry.instance();
			System.out.println("REGISTER :>>>>>:"+registry.toString());
		}
		System.out.println("REGISTER :>>>>>:"+registry.toString());
		prov = registry.getAdapter(info.getProviderName());
		System.out.println("PROVIDER :>>>>>:"+registry.getAdapter(info.getProviderName()));
		DataMap<V> authMap = prov.getAccess().authenticate(info);
		for (Entry<String, V> entry : authMap.map().entrySet()) {
			System.out.println("KEY:"+entry.getKey()+":"+"Value"+":"+entry.getValue());
		}
		System.out.println("Provider OPERATION AUTHMAP"+authMap.toString());
		MultiDataMap<V> multiMap = new MultiDataMap(false, info,authMap);
		prov.getAdapter().setDataMap(multiMap);
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.core.CloudOperation#handle()
	 */
	@Override
	public <T extends Object> CloudOperationOutput<T> handle() throws CloudOperationException {
		preOperation();
		CloudOperationOutput<T> pityOut = null;
		if (isFitToRun()) {
			pityOut = new CloudOperationOutput<T>(info.getProviderName());
			pityOut.set((T) prov.getAdapter().handle());
		}
		postOperation();
		return pityOut;

	}

	@Override
	public void postOperation() {
	}

	@Override
	public boolean canProceed() {
		return false;
	}

	public <B extends BridgeMediationEvent> void bridgeEvent(B evt) {
		Map<String, String> authMap = (Map<String, String>) evt.get();

	}
}
