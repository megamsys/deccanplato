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
import org.megam.deccanplato.provider.Constants;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import static org.megam.deccanplato.provider.salesforce.Constants.*;
import static org.megam.deccanplato.provider.Constants.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 
 * @author pandiyaraja
 *
 **This class implements the business activity of Salesforcecrm leads method.
 * this class has four methods, to implement business functions, like create, update(not implemented now),
 * lisd and delete.
 */
public class LeadsImpl implements BusinessActivity {
     
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;
		
	}

	@Override
	public Map<String, String> run() {
		Map<String, String> outMap=new HashMap<>();
		System.out.println("USER IMPLEMENTATION METHOD RUN METHOD");
		switch (bizInfo.getActivityFunction()) {
		case Constants.CREATE:
			outMap=create(outMap);
			break;
		case Constants.LIST:
			outMap=list(outMap);
			break;
		case Constants.UPDATE:
			outMap=update(outMap);
			break;
		case Constants.DELETE:
			outMap=delete(outMap);
			break;
		default:
			break;
		}
		return outMap;
		
	}

	/**
	 * this method creates a lead in salesforce.com and returns that lead id.
	 * This method gets input from a MAP(contains json data) and returns a MAp.
	 * @param outMap 
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		final String SALESFORCE_CREATE_LEAD_URL = args.get(INSTANCE_URL)+"/services/data/v25.0/sobjects/Lead/";
		
		Map<String,String> header=new HashMap<String,String>();
        header.put(S_AUTHORIZATION, S_OAUTH+args.get(ACCESS_TOKEN));
        System.out.println("ACCESS TOKEN:"+args.get("access_token"));
        					

				
        Map<String, Object> userAttrMap = new HashMap<String, Object>();
        userAttrMap.put("Company", args.get(COMPANY));
        userAttrMap.put("LastName", args.get(LASTNAME));
                
        TransportTools tst=new TransportTools(SALESFORCE_CREATE_LEAD_URL, null, header);
        Gson obj = new GsonBuilder().setPrettyPrinting().create();
        tst.setContentType(ContentType.APPLICATION_JSON, obj.toJson(userAttrMap));
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
        
        outMap.put(OUTPUT, responseBody);

		System.out.println("RESPONSE BPDY" + responseBody);
		return outMap;
		
	}

	/**
	 * this method lists all lead in salesforce.com and returns a list of all lead details.
	 * This method gets input from a MAP(contains json data) and returns a MAp.
	 * @param outMap 
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
		final String SALESFORCE_LIST_LEAD_URL = args.get(INSTANCE_URL)
				+ "/services/data/v25.0/query/?q=SELECT+Company,Id,LastName+FROM+Lead";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(SALESFORCE_LIST_LEAD_URL, null,
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

		outMap.put(OUTPUT, responseBody);

		System.out.println("RESPONSE BPDY" + responseBody);
		return outMap;
	}

	/**
	 * This method updates a lead in salesforce.com and returns a success message with updated lead id.
	 * This method gets input from a MAP(contains json data) and returns a MAp.
	 * @param outMap 
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		
final String SALESFORCE_UPDATE_LEAD_URL = args.get(INSTANCE_URL)+"/services/data/v25.0/sobjects/Lead/"+args.get(ID);
		
		Map<String,String> header=new HashMap<String,String>();
        header.put(S_AUTHORIZATION, S_OAUTH+args.get(ACCESS_TOKEN));
        System.out.println("ACCESS TOKEN:"+args.get("access_token"));
        					

				
        Map<String, Object> userAttrMap = new HashMap<String, Object>();
        userAttrMap.put("Company", args.get(COMPANY));
        userAttrMap.put("LastName", args.get(LASTNAME));
                
        TransportTools tst=new TransportTools(SALESFORCE_UPDATE_LEAD_URL, null, header);
        Gson obj = new GsonBuilder().setPrettyPrinting().create();
        tst.setContentType(ContentType.APPLICATION_JSON, obj.toJson(userAttrMap));
        System.out.println(tst.toString());
        String responseBody = null;
        
        TransportResponse response = null;
        try {
			response=TransportMachinery.patch(tst);
			responseBody=response.entityToString();	
		
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        outMap.put(OUTPUT, responseBody);

		System.out.println("RESPONSE BPDY" + responseBody);
		return outMap;
		
	}

	/**
	 * this method deletes a lead in salesforce.com and returns a success message with deleted lead id.
	 * This method gets input from a MAP(contains json data) and returns a MAp.
	 * @param outMap 
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		final String SALESFORCE_DELETE_LEAD_URL = args.get(INSTANCE_URL)
				+ "/services/data/v25.0/sobjects/Lead/"+args.get(ID);
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH+ args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(SALESFORCE_DELETE_LEAD_URL, null,
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

		outMap.put(OUTPUT, responseBody);

		System.out.println("RESPONSE BPDY" + responseBody);
		return outMap;
	}
	/**
     * this method returns business method name to perform action in that Salesforce Module 
     */
	@Override
	public String name() {
		return "lead";
	}

}
