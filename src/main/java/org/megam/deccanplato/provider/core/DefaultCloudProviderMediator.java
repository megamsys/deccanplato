package org.megam.deccanplato.provider.core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.megam.deccanplato.provider.ProviderOperation;

public class DefaultCloudProviderMediator extends AbstractCloudProviderMediator {

	private LinkedList<CloudOperation> orderedOps = new LinkedList<>();

	public DefaultCloudProviderMediator(RequestData reqData) {
		addOperation(new AccessControlOperation(reqData.getAccess()
				.token(), this));
		addOperation(new ProviderOperation(reqData.getGeneral(), this));
		addOperation(new OutputOperation(reqData.getOutput(), this));
	}

	protected void addOperation(CloudOperation ops) {
		orderedOps.add(ops);
	}
	
	@Override
	public <T> SendBackResponse<T> handleRequest() {
		boolean shouldProceed = true;
		Set<CloudOperationOutput<T>> opsOutSet = new HashSet<>();
		for (Iterator<CloudOperation> iter = orderedOps.iterator(); (iter
				.hasNext() && shouldProceed);) {
			CloudOperation singleOps = iter.next();
			CloudOperationOutput<T> opsOut = singleOps.handle();
			opsOutSet.add(opsOut);
			shouldProceed = singleOps.canProceed();
		}
		/**
		 * grab all the responses from the RequestMediaOperation and stick stuff
		 * into the response data builder
		 ***/
		ResponseData<T> respData = (new ResponseDataBuilder(opsOutSet)).getResponseData();
		return respData;
	}

	public void setSaveableToUse() {

	}

	

}
