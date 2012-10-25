package org.megam.core.api.secure;

import org.megam.core.api.APIValidator;

public class SecurityValidator implements APIValidator {
	
	public boolean validate(AccessToken token) {
/**
 * Interface with DynamoDB to verify the token for 
 * that email/organization/token.
 */
		return true;
	}

}
