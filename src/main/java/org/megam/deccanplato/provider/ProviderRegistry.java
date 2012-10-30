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
package org.megam.deccanplato.provider;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;

public class ProviderRegistry {
    
	public static ProviderRegistry registry;
	// * inject the name of the providers classes using a bean.
	private static Map<String, Provider> providersMap = new HashMap<>();

	// * inject the name of the providers classes using a bean.
	private static Map<String, Set<BusinessActivity>> bizActivityMap = new HashMap<>();

	private ProviderRegistry() {
	}

	public static ProviderRegistry newInstance() {
		if (registry == null) {
			registry = new ProviderRegistry();
		}
		return registry;
	}

	public static ProviderRegistry instance() {
		return registry;
	}

	public Map<String, Provider> getProvidersMap() {
		return providersMap;
	}

	public void setProvidersMap(Map<String, Provider> providersMap) {
		this.providersMap = providersMap;
	}

	public Map<String, Set<BusinessActivity>> getBizActivityMap() {
		return bizActivityMap;
	}

	public void setBizActivityMap(
			Map<String, Set<BusinessActivity>> bizActivityMap) {
		this.bizActivityMap = bizActivityMap;
	}

	public Provider getAdapter(String providerName) {
		return providersMap.get(providerName);
	}

	public Set<BusinessActivity> getBusinessActivitySet(String providerName) {
		return bizActivityMap.get(providerName);
	}

	public BusinessActivity getBusinessActivity(String providerName,
			String activityName) {
		Set<BusinessActivity> activitySet = bizActivityMap.get(providerName);
		for (BusinessActivity searchedActivity : activitySet) {
			if (searchedActivity.name().equalsIgnoreCase(activityName)) {
				return searchedActivity;
			}
		}
		return null;
	}
	/*public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, Provider> entry : providersMap.entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		for (Map.Entry<String, Set<BusinessActivity>> entry1 : bizActivityMap.entrySet()) {
			formatter.format("%10s = %s%n", entry1.getKey(), entry1.getValue());
		}
		formatter.close();
		return strbd.toString();
}*/
}
