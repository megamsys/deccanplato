package org.megam.deccanplato.provider.salesforce;

import java.util.Map;

import org.megam.deccanplato.provider.AbstractProviderAdapter;
import org.megam.deccanplato.provider.BusinessActivity;

public class SalesforceAdapter<T extends Object> extends
		AbstractProviderAdapter<T> {

	private BusinessActivity activity;

	public SalesforceAdapter(Map<String, String> tempArgs) {
		super(tempArgs);
	}

	/* using the user#create key */
	public void configure() {
		activity = registry.getBusinessActivity(cloud_app, business_function);
	}

	/** get the handle responsible for the call and stick stuff into it **/
	public boolean build() {
		activity.setArguments(args);
		return true;
	}

	public T handle() {
		return (T) activity.run();
	}

}
