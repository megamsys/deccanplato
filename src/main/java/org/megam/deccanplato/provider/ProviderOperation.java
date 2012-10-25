package org.megam.deccanplato.provider;

import java.util.Map;

import org.megam.deccanplato.provider.core.AbstractCloudOperation;
import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.AdapterWrapper;
import org.megam.deccanplato.provider.core.CloudMediator;
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
	public void handle() {
		preOperation();
		if (isFitToRun()) {			
			AdapterWrapper runner = new AdapterWrapper(prov.getAdapter());
			// runner.run();
			/*
			 * ResponseData respData = (new
			 * ResponseDataBuilder()).getResponseData(); return respData;
			 */
		}
		postOperation();

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
