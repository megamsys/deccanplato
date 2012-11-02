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
package org.megam.deccanplato.provider.salesforce.handler;

import java.util.Map;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

public class AccountImpl implements BusinessActivity {

	private static final String CREATE="create";
	private static final String LIST="list";
	private static final String UPDATE="update";
	private static final String DELETE="delete";
	private static final String ACCESSTOKEN="access_token";
	private static final String INSTANCEURL="instance_url";
	
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;
	}

	@Override
	public Map<String, String> run() {
		
		System.out.println("ACCOUNT IMPLEMENTATION METHOD RUN METHOD");
		// TODO Write code using TransportMachinery/TransportTools by sending the correct content.
		switch(bizInfo.getActivityFunction()) {
		case CREATE : 
			create();
			break;
		case LIST :
			list();
			break;
		case UPDATE : 
			update();
			break;
		case DELETE :
			delete();
			break;
			default : break;
		}
		
		return null;
	}

	/**
	 * 
	 */
	private Map<String, String> update() {
		
		return null;
	}

	/**
	 * 
	 */
	private Map<String, String> delete() {
		
		return null;
	}

	/**
	 * 
	 */
	private Map<String, String> list() {
		
		return null;
	}

	/**
	 * 
	 */
	private Map<String, String> create() {
		
		System.out.println("ACCOUNT CREATE");
		final String SALESFORCE_CREATE_USER_URL = args.get(INSTANCEURL)+"/services/data/v25.0/sobjects/Account/";
		return null;
	}

	@Override
	public String name() {
		return "account";
	}

}
