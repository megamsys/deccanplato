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
package org.megam.deccanplato.provider.zoho.crm.handler;

import java.util.Map;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

public class AccountImpl implements BusinessActivity {

	private static final String CREATE="create";
	private static final String LIST="list";
	private static final String UPDATE="update";
	private static final String DELETE="delete";
	private static final String AUTHTOKEN="OAuth_token";
	private static final String SCOPE="crmapi";
	private static final String TYPE="AllUsers";
	
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;
		
	}

	@Override
	public Map<String, String> run() {
		
		System.out.println("USER IMPLEMENTATION METHOD RUN METHOD");
		switch (bizInfo.getActivityFunction()) {
		case CREATE:
			create();
			break;
		case LIST:
			list();
			break;
		case UPDATE:
			update();
			break;
		case DELETE:
			delete();
			break;
		default:
			break;
		}
		return null;
	}

	/**
	 * 
	 */
	private void create() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private void list() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private void update() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String name() {
		return "user";
	}

}
