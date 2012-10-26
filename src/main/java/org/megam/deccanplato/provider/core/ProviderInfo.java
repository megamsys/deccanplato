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

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class ProviderInfo implements DataMap {

	private Map<String, String> business_activity = new HashMap<String, String>();
	private Map<String, String> access = new HashMap<String, String>();

	
	private static final String PROVIDER = "provider";


	public ProviderInfo() {	
		
	}


	public Map<String, String> map() {
		if(!business_activity.keySet().containsAll(access.keySet())) {
			business_activity.putAll(access);
		}
		return business_activity;
	}
	
	public String getProviderName() {
		return map().get(PROVIDER);
	}
	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, String> entry : map().entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		return strbd.toString();
	}

	

}
