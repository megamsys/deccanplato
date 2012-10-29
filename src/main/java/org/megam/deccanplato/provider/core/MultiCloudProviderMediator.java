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

import java.util.LinkedHashSet;
import java.util.Set;

public class MultiCloudProviderMediator extends AbstractCloudProviderMediator {

	private Set<CloudMediator> orderedMultiMedSet = new LinkedHashSet<>();

	public MultiCloudProviderMediator(RequestData reqData) {
	}

	public void addRequestMediator(CloudMediator mediator) {
		orderedMultiMedSet.add(mediator);
	}
	
	@Override
	public <T> SendBackResponse<T> handleRequest() throws CloudMediatorException {
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
