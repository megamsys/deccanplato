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

public class UserImpl implements BusinessActivity {

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;

	@Override
	public String name() {
		return bizInfo.getName();
	}

	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;
	}

	@Override
	public Map<String, String> run() {
		// TODO Write code using TransportMachinery/TransportTools by sending the correct content.
		switch(bizInfo.getActivity()) {
		case CREATE : 
			create();
			break;
		case INSERT :
			insert();
			break;
		case UPDATE : 
			delete();
			break;
		case DELETE :
			delete();
			break;
			default : break;
		}
		return null;
	}

	private void create() {

	}

	private void insert() {

	}

	private void delete() {

	}

	public void update() {

	}
}
