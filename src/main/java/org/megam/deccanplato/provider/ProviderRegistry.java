package org.megam.deccanplato.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;


public class ProviderRegistry {

	private static ProviderRegistry registry;
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

}
