/* 
 ** Copyright [2012] [Megam Systems]
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 **
 ** http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 */
package org.megam.deccanplato.provider.xero;

import static org.megam.deccanplato.provider.xero.Constants.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.client.OAuthClient;
import net.oauth.client.OAuthResponseMessage;

import org.megam.core.api.secure.AccessToken;
import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultDataMap;

import com.google.api.client.auth.oauth2.Credential;
import com.google.gdata.client.GoogleAuthTokenFactory.OAuth2Token;
import com.rossjourdain.util.xero.XeroClient;
import com.rossjourdain.util.xero.XeroClientProperties;

/**
 * @author pandiyaraja
 * 
 */
public class XeroAdapterAccess implements AdapterAccess {

	private boolean success = false;
    private OAuthAccessor accessor;
    OAuthMessage response;
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.deccanplato.provider.core.AdapterAccess#isSuccessful()
	 */
	public XeroAdapterAccess() {
		super();
	}

	@Override
	public boolean isSuccessful() {
		// TODO Auto-generated method stub
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.megam.deccanplato.provider.core.AdapterAccess#authenticate(org.megam
	 * .deccanplato.provider.core.DataMap)
	 */
	@Override
	public <T> DataMap<T> authenticate(DataMap<T> access)
			throws AdapterAccessException {
		XeroClient xeroClient;
		success = true;
		return access;
	}
	

}
