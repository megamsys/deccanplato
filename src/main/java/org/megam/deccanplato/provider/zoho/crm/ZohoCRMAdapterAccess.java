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
package org.megam.deccanplato.provider.zoho.crm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultDataMap;
import static org.megam.deccanplato.provider.zoho.crm.Constants.*;

public class ZohoCRMAdapterAccess implements AdapterAccess {

	private boolean success = false;

	private static final String ZOHO_AUTH_URL = "https://accounts.zoho.com/apiauthtoken/nb/create?";
	private static final String SCOPE = "scope";
	private static final String EMAIL_ID = "email_id";
	private static final String PASSWORD = "password";

	@Override
	public boolean isSuccessful() {
		return success;
	}

	@Override
	public <T extends Object> DataMap<T> authenticate(DataMap<T> access)
			throws AdapterAccessException {
		Map<String, T> accessMap = access.map();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("SCOPE", "ZohoCRM/crmapi"));
		list.add(new BasicNameValuePair("EMAIL_ID", (String) accessMap
				.get(EMAIL_ID)));
		list.add(new BasicNameValuePair("PASSWORD", (String) accessMap
				.get(PASSWORD)));
		

		TransportTools tools = new TransportTools(ZOHO_AUTH_URL, list);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.post(tools);
			responseBody = response.entityToString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parseOutput(responseBody);
	}

	public <T> DataMap<T> parseOutput(String response) throws AdapterAccessException {

		DataMap<T> respMap = new DefaultDataMap<T>();
		try {
			AccessParser ap = new AccessParser(response);
			respMap.map().put(AUTHTOKEN, (T) ap.getAuthtoken());
			success=true;
		} catch (IOException e) {
			throw new AdapterAccessException(
					"Failed to parse JSON received from the post operation.", e);
		}
		
		return respMap;
	}

}
