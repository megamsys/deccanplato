package org.megam.core.api.secure;

import org.megam.core.api.APIValidator;

public class FakeTokenValidator implements APIValidator{

	@Override
	public boolean validate(AccessToken token) {
		return true;
	}

	
}
