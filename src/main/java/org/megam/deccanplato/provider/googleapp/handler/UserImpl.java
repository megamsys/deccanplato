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
package org.megam.deccanplato.provider.googleapp.handler;

import static org.megam.deccanplato.provider.Constants.CREATE;
import static org.megam.deccanplato.provider.Constants.DELETE;
import static org.megam.deccanplato.provider.Constants.LIST;
import static org.megam.deccanplato.provider.Constants.UPDATE;

import java.util.HashMap;
import java.util.Map;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

public class UserImpl implements BusinessActivity {

	BusinessActivityInfo bizInfo;
	Map<String, String> args = new HashMap<String, String>();
	
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		
		this.bizInfo=tempBizInfo;
		this.args=tempArgs;
	}

	@Override
	public Map<String, String> run() {
		
		Map<String, String> outMap = new HashMap<String, String>();
		System.out.println("USER IMPLEMENTATION METHOD RUN METHOD");
		switch (bizInfo.getActivityFunction()) {
		case CREATE:
			outMap = create(outMap);
			break;
		case LIST:
			outMap = list(outMap);
			break;
		case UPDATE:
			outMap = update(outMap);
			break;
		case DELETE:
			outMap = delete(outMap);
			break;
		default:
			break;
		}

		return outMap;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name() {
		return "user";
	}

}
