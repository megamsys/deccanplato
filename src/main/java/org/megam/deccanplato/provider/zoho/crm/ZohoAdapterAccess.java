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
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultDataMap;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.google.gson.Gson;

public class ZohoAdapterAccess implements AdapterAccess {
	
	private boolean success = false;
	private static final String ZOHO_OAUTH2_URL = "https://accounts.zoho.com/apiauthtoken/nb/create?";
    
	@Override
	public boolean isSuccessful() {
		return success;
	}

	@Override
	public<T extends Object> DataMap<T> authenticate(DataMap<T> access) {
		Map<String,T> accessMap = access.map();
		
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("SCOPE", "ZohoCRM/crmapi"));
        list.add(new BasicNameValuePair("EMAIL_ID", (String) accessMap.get("EMAIL_ID")));
        list.add(new BasicNameValuePair("PASSWORD", (String) accessMap.get("PASSWORD")));
        
        
        TransportTools tools=new TransportTools(ZOHO_OAUTH2_URL, list);
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

	@Override
	public <T> DataMap<T> parsOutput(String response, DataMap<T> map) {
		
		AccessParser ap = null;
		try {
			   ap=new AccessParser(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			map.map().put("OAuth_token", (T) ap.getAuthtoken());	
		return map;
	}
	
	 
}
