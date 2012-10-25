package org.megam.deccanplato.provider;

import java.util.Map;

import org.megam.deccanplato.provider.core.AbstractCloudOperation;
import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.CloudMediator;
import org.megam.deccanplato.provider.core.CloudOperationOutput;
import org.megam.deccanplato.provider.core.GeneralInfo;
import org.megam.deccanplato.provider.event.BridgeMediationEvent;
import org.springframework.beans.factory.annotation.Autowired;

public class ProviderOperation extends AbstractCloudOperation {

	@Autowired
	private ProviderRegistry registry;

	private GeneralInfo info;
	private Provider prov;

	private boolean isFitToRun = false;

	public ProviderOperation(GeneralInfo tempInfo, CloudMediator tempParent) {
		super(tempParent);
		this.info = tempInfo;
	}

	public boolean isFitToRun() {
		return isFitToRun;
	}

	public void preOperation() {
		prov = registry.getAdapter(info.getProviderName());
		AdapterAccess accessSource = prov.getAdapterAccess();
		isFitToRun = accessSource.isSuccessful();
	}

	@Override
	public <T extends Object> CloudOperationOutput<T> handle() {
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
		/** 
		 ***/
		Map<String, String> authMap = (Map<String, String>) evt.get();

	}
}
