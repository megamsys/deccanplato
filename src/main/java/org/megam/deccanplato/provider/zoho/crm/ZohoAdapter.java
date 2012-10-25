package org.megam.deccanplato.provider.zoho.crm;

import java.util.Map;

import org.megam.deccanplato.provider.AbstractProviderAdapter;
import org.megam.deccanplato.provider.BusinessActivity;

public class ZohoAdapter extends AbstractProviderAdapter{

private BusinessActivity activity;
	
	public  ZohoAdapter() {
		super();
	}
	
	public ZohoAdapter(Map<String, String> tempArgs) {
		super(tempArgs);
	}

	public void configure() {
		/* using the user#create key */
		//activity = registry.getBusinessActivity(cloud_app, business_function);
	}

	public boolean build() {
		/** get the handle responsible for the call and stick stuff into it **/
		//activity.setArguments(args);
		return true;
	}
	
	public Map<String,String> handle() {
		//return activity.run();
		return null;
	}
	 
}
