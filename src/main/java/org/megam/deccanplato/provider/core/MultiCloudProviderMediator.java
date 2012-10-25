package org.megam.deccanplato.provider.core;

import java.util.LinkedHashSet;
import java.util.Set;

public class MultiCloudProviderMediator extends AbstractCloudProviderMediator {

	private Set<CloudMediator> orderedMultiMedSet = new LinkedHashSet<>();

	public MultiCloudProviderMediator(RequestData reqData) {
	}

	public void addRequestMediator(CloudMediator mediator) {
		orderedMultiMedSet.add(mediator);
	}

	public SendBackResponse handleRequest() {
		MultiResponseData resp = new MultiResponseData();
		for (CloudMediator singleMediator : orderedMultiMedSet) {
			resp.add(singleMediator.handleRequest());
		}
		return resp;
	}

	@Override
	public void setSaveableToUse() {

	}

}
