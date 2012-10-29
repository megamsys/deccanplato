/* “Copyright 2012 Megam Systems”
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.megam.deccanplato.provider.core;

import org.megam.core.api.secure.AccessToken;
import org.megam.core.api.secure.SecurityValidator;

public class AccessControlOperation extends  AbstractCloudOperation {

	private AccessToken token;
	private SecurityValidator validator;
	private boolean goAhead = false;
	
	public AccessControlOperation(AccessToken tempToken, CloudMediator tempParent) {
		super(tempParent);
		System.out.println(tempToken);
		getParent().registerCloudBridgeListener(this);
		this.token=tempToken;
	}

	public boolean isFitToRun() {
		return true;  		
	}

	@Override
	public void preOperation() {
		// Setup the call to the cache, if its not available then perform the
		// call. The cache thingly will get handled in Security Validator
		validator = new SecurityValidator();
		System.out.println(validator.toString());
	}

	public <T> CloudOperationOutput<T> handle() {
		if (isFitToRun()) {
			preOperation();
              System.out.println(token);
			if (token != null && validator!=null) {
				System.out.println("Token"+token+":"+"validator"+validator);
				/**
				 * This performs the API check of the token to ensure that the
				 * requesting application can access it This is a dynamo DB call
				 * or something else.
				 */
				goAhead=validator.validate(token);
			    
			}
			postOperation();
		}
		return null;
	}

	public void postOperation() {
		// TODO Auto-generated method stub
		
	}

	public boolean canProceed() {
		return goAhead;
	}

	


}
