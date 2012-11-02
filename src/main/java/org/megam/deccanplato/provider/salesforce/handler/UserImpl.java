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
package org.megam.deccanplato.provider.salesforce.handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultDataMap;

public class UserImpl implements BusinessActivity {

	private static final String CREATE = "create";
	private static final String LIST = "list";
	private static final String UPDATE = "update";
	private static final String DELETE = "delete";

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;

	@Override
	public String name() {
		System.out.println("USER IMPL :NAME" + bizInfo);
		return "user";
	}

	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;
		System.out.println("USER IMPL :SET ARGUMENTS" + tempArgs);
	}

	@Override
	public Map<String, String> run() {
		System.out.println("USER IMPLEMENTATION METHOD RUN METHOD");
		switch (bizInfo.getActivityFunction()) {
		case CREATE:
			create();
			break;
		case LIST:
			list();
			break;
		case UPDATE:
			delete();
			break;
		case DELETE:
			delete();
			break;
		default:
			break;
		}
		return null;
	}

	private Map<String, String> create() {

		final String SALESFORCE_CREATE_USER_URL = args.get("instance_url")
				+ "/services/data/v25.0/sobjects/User/";
		System.out.println("Bfore call of timezone");
		// Timezone tz=new Timezone();
		System.out.println("After call timezone");
		System.out.println("IN CREATE USER METHOD");
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "OAuth " + args.get("access_token"));
		System.out.println("ACCESS TOKEN:" + args.get("access_token"));
		Locale locale = new Locale(args.get("language"), args.get("locale"));

		List<NameValuePair> userAttrList = new ArrayList<NameValuePair>();
		userAttrList.add(new BasicNameValuePair("Username", args
				.get("user_name")));
		userAttrList.add(new BasicNameValuePair("FirstName", args
				.get("first_name")));
		userAttrList.add(new BasicNameValuePair("Email", args.get("email")));
		userAttrList.add(new BasicNameValuePair("Alias", args.get("alias")));
		userAttrList.add(new BasicNameValuePair("ProfileId", args
				.get("profile")));
		userAttrList.add(new BasicNameValuePair("LastName", args
				.get("last_name")));
		userAttrList.add(new BasicNameValuePair("TimeZoneSidKey", args
				.get("time_zone")));
		userAttrList.add(new BasicNameValuePair("LocaleSidKey", locale
				.toString()));
		userAttrList.add(new BasicNameValuePair("EmailEncodingKey", args
				.get("charset_encoding")));
		userAttrList.add(new BasicNameValuePair("LanguageLocaleKey", locale
				.toString()));

		TransportTools tst = new TransportTools(SALESFORCE_CREATE_USER_URL,
				userAttrList, header);

		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.post(tst);
			responseBody = response.entityToString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String, String> respMap = new HashMap<>();

		System.out.println("RESPONSE BPDY" + responseBody);
		return respMap;
	}

	private Map<String, String> list() {

		final String SALESFORCE_LIST_USER_URL = args.get("instance_url")
				+ "/services/data/v25.0/query/?q=SELECT+Username+FROM+User";
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "OAuth " + args.get("access_token"));

		TransportTools tst = new TransportTools(SALESFORCE_LIST_USER_URL, null,
				header);
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

		Map<String, String> respMap = new HashMap<>();

		System.out.println("RESPONSE BPDY" + responseBody);
		return respMap;

	}

	private void delete() {

	}

	public void update() {

	}

}
