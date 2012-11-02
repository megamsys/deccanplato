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
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AccountImpl implements BusinessActivity {

	private static final String CREATE="create";
	private static final String LIST="list";
	private static final String UPDATE="update";
	private static final String DELETE="delete";
	private static final String ACCESSTOKEN="access_token";
	private static final String INSTANCEURL="instance_url";
	private static final String NAME="first_name";
	private static final String ID="id";
	
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;
	}

	@Override
	public Map<String, String> run() {
		
		System.out.println("ACCOUNT IMPLEMENTATION METHOD RUN METHOD");
		// TODO Write code using TransportMachinery/TransportTools by sending the correct content.
		switch(bizInfo.getActivityFunction()) {
		case CREATE : 
			create();
			break;
		case LIST :
			list();
			break;
		case UPDATE : 
			update();
			break;
		case DELETE :
			delete();
			break;
			default : break;
		}
		
		return null;
	}

	/**
	 * 
	 */
	private Map<String, String> update() {
		
		return null;
	}

	/**
	 * 
	 */
	private Map<String, String> delete() {
		
		final String SALESFORCE_DELETE_ACCOUNT_URL = args.get(INSTANCEURL)
				+ "/services/data/v25.0/sobjects/Account/"+args.get(ID);
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "OAuth " + args.get(ACCESSTOKEN));

		TransportTools tst = new TransportTools(SALESFORCE_DELETE_ACCOUNT_URL, null,
				header);
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.delete(tst);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseBody = response.entityToString();

		Map<String, String> respMap = new HashMap<>();

		System.out.println("RESPONSE BPDY" + responseBody);
		return respMap;
		
	}

	/**
	 * 
	 */
	private Map<String, String> list() {
		
		final String SALESFORCE_LIST_ACCOUNT_URL = args.get(INSTANCEURL)
				+ "/services/data/v25.0/query/?q=SELECT+Name,Id+FROM+Account";
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "OAuth " + args.get(ACCESSTOKEN));

		TransportTools tst = new TransportTools(SALESFORCE_LIST_ACCOUNT_URL, null,
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

	/**
	 * 
	 */
	private Map<String, String> create() {
		
		System.out.println("ACCOUNT CREATE");
		final String SALESFORCE_CREATE_USER_URL = args.get(INSTANCEURL)+"/services/data/v25.0/sobjects/Account/";
		Map<String,String> header=new HashMap<String,String>();
        header.put("Authorization", "OAuth "+args.get(ACCESSTOKEN));
        Map<String, Object> accountAttrMap = new HashMap<String, Object>();
        accountAttrMap.put("Name", args.get(NAME));
        
        TransportTools tst=new TransportTools(SALESFORCE_CREATE_USER_URL, null, header);
        Gson obj = new GsonBuilder().setPrettyPrinting().create();
        tst.setContentType(ContentType.APPLICATION_JSON, obj.toJson(accountAttrMap));
        System.out.println(tst.toString());
        String responseBody = null;
        
        TransportResponse response = null;
        try {
			response=TransportMachinery.post(tst);
			responseBody=response.entityToString();	
		
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String, String> respMap = new HashMap<>();

		System.out.println("RESPONSE BPDY" + responseBody);
		return respMap;		
	}

	@Override
	public String name() {
		return "account";
	}

}
