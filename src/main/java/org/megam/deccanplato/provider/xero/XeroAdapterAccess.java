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
import net.oauth.client.httpclient3.HttpClient3;

import org.megam.core.api.secure.AccessToken;
import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultDataMap;

import com.google.api.client.auth.oauth2.Credential;
import com.google.gdata.client.GoogleAuthTokenFactory.OAuth2Token;

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
		/*
		DataMap<T> respMap = new DefaultDataMap<T>();
		Map<String, T> accessMap = access.map();
		try {
			 accessor = new OAuthAccessor(new OAuthConsumer(null,
					(String) accessMap.get(CONSUMER_KEY),
					(String) accessMap.get(CONSUMER_SECRET), null));
			OAuthClient client = new OAuthClient(new HttpClient3());
			response = client.invoke(accessor, OAuthMessage.GET,
					TOKEN_URL, null);

			respMap.map().put(TOKEN_ID, (T) response.getToken());
			System.out.println("ADPTR ACCESS" + response.getToken());
			OAuthClient client1 = new OAuthClient(new HttpClient3());

			Properties paramProps = new Properties();
            paramProps.setProperty("oauth_token",
                    response.getToken());
            //System.out.println(accessor.consumer.serviceProvider.userAuthorizationURL+":URL");
			OAuthMessage response1 = sendRequest(paramProps, "https://api.xero.com/oauth/Authorize");
			System.out.println(response1.URL);
			
			
			Properties paramProps1 = new Properties();
            paramProps1.setProperty("oauth_token", (String) accessMap.get(XERO_ID));
            OAuthMessage response2 = sendRequest(paramProps, "https://api.xero.com/oauth/AccessToken");
            System.out.println(response2.getToken());
            success = true;
		} catch (OAuthProblemException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		access.map().put("access_token", (T) "GV3R2IY8K7TPMUMB0NH9QNVN4NPFKF");
		Map<String, T> accessMap = access.map();
		success = true;
		return access;
	}
	private OAuthMessage sendRequest(Map map, String url) throws IOException,
    URISyntaxException, OAuthException
    {
		List<Map.Entry> params = new ArrayList<Map.Entry>();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry p = (Map.Entry) it.next();
			params.add(new OAuth.Parameter((String)p.getKey(),
            (String)p.getValue()));
		}
		accessor.tokenSecret = "TG77XPLAGLSGA7LAQIAW5SVA9RQGDT";
		OAuthClient client = new OAuthClient(new HttpClient3());
		return client.invoke(accessor, "GET",  url, params);
    }

}
