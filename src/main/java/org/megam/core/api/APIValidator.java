package org.megam.core.api;

import org.megam.core.api.secure.AccessToken;

public interface APIValidator {
	
	public boolean validate(AccessToken token);

}
