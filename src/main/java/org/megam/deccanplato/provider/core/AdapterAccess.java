package org.megam.deccanplato.provider.core;

import java.util.Map;

public interface AdapterAccess {
	
	public boolean isSuccessful();
	
	public DataMap authenticate(DataMap accessMap);
	
}
