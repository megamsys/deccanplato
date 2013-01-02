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
package org.megam.deccanplato.provider.xero.handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.xero.Constants.*;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.OAuthServiceProvider;
import net.oauth.client.OAuthClient;
import net.oauth.client.httpclient3.HttpClient3;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

public class AccountImpl implements BusinessActivity {

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;		
	}

	@Override
	public Map<String, String> run() {
		Map<String, String> outMap=new HashMap<String, String>();
		switch(bizInfo.getActivityFunction()) {
		case LIST:
			outMap=list(outMap);
			break;
		}
		return outMap;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
		final String ACCOUNT_URL="https://api.xero.com/api.xro/2.0/Accounts";
		/*List<NameValuePair> accountList=new ArrayList<NameValuePair>();
		accountList.add(new BasicNameValuePair("atoken", "HTBCSF4GIURZHGDKELGUQKNDFO8COK"));
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization:", "HTBCSF4GIURZHGDKELGUQKNDFO8COK");

		TransportTools tst = new TransportTools(ACCOUNT_URL, accountList,
				null);
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.get(tst);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseBody = response.entityToString();
		System.out.println(responseBody+"RESPONSE");
		outMap.put(OUTPUT, responseBody);
		return outMap;*/
		try {
			
			OAuthClient client = new OAuthClient(new HttpClient3());
			OAuthServiceProvider provider=new OAuthServiceProvider(REQUEST_TOKEN_URL, AUTHORIZATION_URL, ACCESS_TOKEN_URL);
			
			OAuthConsumer consumer=new OAuthConsumer(null, args.get(CONSUMER_KEY), "RQ86WBQNPF9XG3SZWC5ZCFCULXPKCV", provider);
			OAuthAccessor accessor=new OAuthAccessor(consumer);
			accessor.accessToken=args.get(OAUTH_TOKEN);
			accessor.tokenSecret="RQ86WBQNPF9XG3SZWC5ZCFCULXPKCV";
			client.invoke(accessor, OAuthMessage.GET, ACCOUNT_URL, null);
			
			
			//System.out.println(response.toString()+"RESPONSE");
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return outMap;
	}

	@Override
	public String name() {
		return "account";
	}

}
