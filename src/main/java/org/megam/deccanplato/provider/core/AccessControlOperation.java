package org.megam.deccanplato.provider.core;

import org.megam.core.api.secure.AccessToken;
import org.megam.core.api.secure.SecurityValidator;

public class AccessControlOperation extends  AbstractCloudOperation {

	private AccessToken token;
	private SecurityValidator validator;
	private boolean goAhead = false;
	
	public AccessControlOperation(AccessToken tempToken, CloudMediator tempParent) {
		super(tempParent);
		getParent().registerCloudBridgeListener(this);
	}

	public boolean isFitToRun() {
		return true;  		
	}

	@Override
	public void preOperation() {
		// Setup the call to the cache, if its not available then perform the
		// call. The cache thingly will get handled in Security Validator
		validator = new SecurityValidator();
	}

	public void handle() {
		if (isFitToRun()) {
			preOperation();

			if (token != null && validator!=null) {
				/**
				 * This performs the API check of the token to ensure that the
				 * requesting application can access it This is a dynamo DB call
				 * or something else.
				 */
				validator.validate(token);
			}
			postOperation();
		}
	}

	public void postOperation() {
		// TODO Auto-generated method stub
		
	}

	public boolean canProceed() {
		return goAhead;
	}

	


}
