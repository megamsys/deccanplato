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

import org.megam.deccanplato.provider.core.AbstractCloudOperation;
import org.megam.deccanplato.provider.core.CloudMediator;
import org.megam.deccanplato.provider.core.CloudOperationException;
import org.megam.deccanplato.provider.core.CloudOperationOutput;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.MultiDataMap;
import org.megam.deccanplato.provider.core.ProviderInfo;
import org.megam.deccanplato.provider.event.BridgeMediationEvent;
import org.springframework.beans.factory.annotation.Autowired;

public class ProviderOperation extends AbstractCloudOperation {

	@Autowired
	private ProviderRegistry registry;

	private ProviderInfo info;
	private Provider prov;

	public ProviderOperation(ProviderInfo tempInfo, CloudMediator tempParent) {
		super(tempParent);
		this.info = tempInfo;
	}

	public boolean isFitToRun() {
		if (prov != null && prov.getAdapterAccess() != null) {
			return prov.getAdapterAccess().isSuccessful();
		}

		return false;
	}

	public void preOperation() throws CloudOperationException {
		prov = registry.getAdapter(info.getProviderName());
		DataMap authMap = prov.getAdapterAccess().authenticate(info);
		MultiDataMap multiMap = new MultiDataMap(false, (DataMap)info,authMap);
		prov.getAdapter().setDataMap(multiMap);
	}

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
