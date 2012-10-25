package org.megam.deccanplato.provider;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class ProviderRegistry {

	private static ProviderRegistry registry;
	// * inject the name of the providers classes using a bean.
	private static ConcurrentMap<String, Provider> providersMap = new ConcurrentHashMap<>();
	// * inject the name of the providers classes using a bean.
	private static ConcurrentMap<String, Set<BusinessActivity>> bizActivityMap = new ConcurrentHashMap<>();

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
