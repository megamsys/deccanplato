package org.megam.deccanplato.provider.controller;

import org.megam.deccanplato.provider.core.CloudMediator;
import org.megam.deccanplato.provider.core.DefaultCloudProviderMediator;
import org.megam.deccanplato.provider.core.MultiCloudProviderMediator;
import org.megam.deccanplato.provider.core.RequestData;

public class AdapterHelper {

	private boolean chained;

	public AdapterHelper() {
	}
	
	public CloudMediator mediator(RequestData reqData) {
		CloudMediator mediator = null;
		if(isChained()) {
			mediator = new MultiCloudProviderMediator(reqData);
		} else {
			mediator = new DefaultCloudProviderMediator(reqData);
		}
		return mediator;
	}

	public boolean isChained() {
		return chained;
	}

	public void setChained(boolean tempChained) {
		this.chained = tempChained;
	}

}
