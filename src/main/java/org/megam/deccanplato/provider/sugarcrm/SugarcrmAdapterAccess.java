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
package org.megam.deccanplato.provider.sugarcrm;

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

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

public class SugarcrmAdapterAccess implements AdapterAccess {
	
	private boolean success = false;
	private static final String SALESFORCE_OAUTH2_URL = "https://login.salesforce.com/services/oauth2/token";
    
	@Override
	public boolean isSuccessful() {
		return success;
	}

	@Override
	public<T extends Object> DataMap<T> authenticate(DataMap<T> access) throws AdapterAccessException {
		Map<String,T> accessMap = access.map();
		
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("grant_type", "password"));
        list.add(new BasicNameValuePair("client_id", (String) accessMap.get("consumer_key")));
        list.add(new BasicNameValuePair("client_secret", (String) accessMap.get("consumer_secret")));
        list.add(new BasicNameValuePair("username", (String) accessMap.get("access_username")));
        list.add(new BasicNameValuePair("password", (String) accessMap.get("access_password")));
        
        TransportTools tools=new TransportTools(SALESFORCE_OAUTH2_URL, list);
        String responseBody = null;
        
        TransportResponse response = null;
        try {
			response=TransportMachinery.post(tools);
			responseBody=response.entityToString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
      
        DataMap<T> respMap = new DefaultDataMap<T>();
        
        System.out.println("RESPONSE BPDY"+responseBody);
        respMap=parsOutput(responseBody, respMap);
        return respMap;
	}

	public <T> DataMap<T> parsOutput(String response, DataMap<T> map) {
		
		JSONObject json;
		try {
			    json=new JSONObject(response); 
			    map.map().put("access_token", (T) json.get("access_token"));
			    map.map().put("instance_url", (T) json.get("instance_url"));
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	 
}
