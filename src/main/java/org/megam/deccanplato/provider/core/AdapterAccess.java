package org.megam.deccanplato.provider.core;

import java.util.Map;

public interface AdapterAccess {
	
	public boolean isSuccessful();
	
	public Map<String, String> authenticate();
	
}
