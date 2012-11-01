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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.megam.deccanplato.provider.ProviderOperation;

public class DefaultCloudProviderMediator extends AbstractCloudProviderMediator {

	private LinkedList<CloudOperation> orderedOps = new LinkedList<>();

	public DefaultCloudProviderMediator(RequestData reqData) {
		//System.out.println(reqData);
		addOperation(new AccessControlOperation(reqData.getAccess().token(), this));
		System.out.println("Access Controller Token"+reqData.getAccess().token());
		addOperation(new ProviderOperation(reqData.getGeneral(), this));
		addOperation(new OutputOperation(reqData.getOutput(), this));
		System.out.println(reqData.getAccess().token());
		
	}

	protected void addOperation(CloudOperation ops) {
		orderedOps.add(ops);
		System.out.println(ops);
	}

	@Override
	public <T> SendBackResponse<T> handleRequest()
			throws CloudMediatorException {
		boolean shouldProceed = true;
		Set<CloudOperationOutput<T>> opsOutSet = new HashSet<>();
		try {
			for (Iterator<CloudOperation> iter = orderedOps.iterator(); (iter
					.hasNext() && shouldProceed);) {
				System.out.println("DFPM OrderedOps:"+iter.next());
				CloudOperation singleOps = iter.next();
				System.out.println("SINGLEOPS>>>>>>>>>>>>>>"+singleOps);
				CloudOperationOutput<T> opsOut = singleOps.handle();
				opsOutSet.add(opsOut);
				shouldProceed = singleOps.canProceed();				
			}
		} catch (CloudOperationException coe) {
			throw new CloudMediatorException("An error occurred while executing a cloud operation", coe);
		}
		/**
		 * grab all the responses from the RequestMediaOperation and stick stuff
		 * into the response data builder
		 ***/
		ResponseData<T> respData = (new ResponseDataBuilder(opsOutSet))
				.getResponseData();
		System.out.println("RESPONSE DATA"+respData);
		return respData;
	}

	public void setSaveableToUse() {

	}

}
