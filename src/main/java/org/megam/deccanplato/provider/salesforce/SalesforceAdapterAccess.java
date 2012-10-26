package org.megam.deccanplato.provider.salesforce;

import java.util.Map;

import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.DataMap;

public class SalesforceAdapterAccess implements AdapterAccess {

	@Override
	public boolean isSuccessful() {
		return false;
	}

	@Override
	public DataMap authenticate(DataMap accessMap) {
		return null;
	}

}
