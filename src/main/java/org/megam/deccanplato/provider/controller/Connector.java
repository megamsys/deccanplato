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
package org.megam.deccanplato.provider.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.megam.deccanplato.provider.core.CloudMediator;
import org.megam.deccanplato.provider.core.DefaultCloudProviderMediator;
import org.megam.deccanplato.provider.core.MultiCloudProviderMediator;
import org.megam.deccanplato.provider.core.RequestData;
/**
 * Base Connector which provides the necessary helper methods.
 * @author ram
 *
 */
public class Connector {

	private boolean chained=false;

	public Connector() {
	}
	
	/**
	 * Figures out the mediator for the json parsed  request data.
	 * By default, it is considered as not multi chainded mediator. 
	 * @param reqData
	 * @return
	 */
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
	
	public String urlDecoder(String json) throws UnsupportedEncodingException {
		String result = URLDecoder.decode(json, "UTF-8");
		return result;
	}

}
