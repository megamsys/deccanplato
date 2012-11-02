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
    
	private static final String CREATE="create";
	private static final String LIST="list";
	private static final String UPDATE="update";
	private static final String DELETE="delete";
	private static final String Username="user_name";
	private static final String FirstName="first_name";
	private static final String Email="email";
	private static final String Alias="alias";
	private static final String ProfileId="profile";
	private static final String LastName="last_name";
	private static final String TimeZoneSidKey="time_zone";
	private static final String LocaleSidKey="locale";
	private static final String EmailEncodingKey="charset_encoding";
	private static final String LanguageLocaleKey="language";
	private static final String ACCESSTOKEN="access_token";
	private static final String INSTANCEURL="instance_url";
	
	
	
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	
    
	@Override
	public String name() {
		System.out.println("USER IMPL :NAME"+bizInfo);
		return "user";
	}

	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;
		System.out.println("USER IMPL :SET ARGUMENTS"+tempArgs);
	}

	@Override
	public Map<String, String> run() {
		System.out.println("USER IMPLEMENTATION METHOD RUN METHOD");
		// TODO Write code using TransportMachinery/TransportTools by sending the correct content.
		switch(bizInfo.getActivityFunction()) {
		case CREATE : 
			create();
			break;
		case LIST :
			list();
			break;
		case UPDATE : 
			delete();
			break;
		case DELETE :
			delete();
			break;
			default : break;
		}
		return null;
	}

	private Map<String, String> create() {
		
		final String SALESFORCE_CREATE_USER_URL = args.get(INSTANCEURL)+"/services/data/v25.0/sobjects/User/";
		System.out.println("Bfore call of timezone");
		//Timezone tz=new Timezone();
		System.out.println("After call timezone");
		System.out.println("IN CREATE USER METHOD");
        Map<String,String> header=new HashMap<String,String>();
        header.put("Authorization", "OAuth "+args.get(ACCESSTOKEN));
        System.out.println("ACCESS TOKEN:"+args.get("access_token"));
        Locale locale=new Locale(args.get("language"),args.get(LocaleSidKey));					

				
        List<NameValuePair> userAttrList = new ArrayList<NameValuePair>();
        userAttrList.add(new BasicNameValuePair("Username", args.get(Username)));
        userAttrList.add(new BasicNameValuePair("FirstName", args.get(FirstName)));
        userAttrList.add(new BasicNameValuePair("Email", args.get(Email)));
        userAttrList.add(new BasicNameValuePair("Alias", args.get(Alias)));
        userAttrList.add(new BasicNameValuePair("ProfileId", args.get(ProfileId)));
        userAttrList.add(new BasicNameValuePair("LastName", args.get(LastName)));
        userAttrList.add(new BasicNameValuePair("TimeZoneSidKey", args.get(TimeZoneSidKey)));
        userAttrList.add(new BasicNameValuePair("LocaleSidKey", locale.toString()));
        userAttrList.add(new BasicNameValuePair("EmailEncodingKey", args.get(EmailEncodingKey)));
        userAttrList.add(new BasicNameValuePair("LanguageLocaleKey", locale.toString()));
        
		TransportTools tst=new TransportTools(SALESFORCE_CREATE_USER_URL, userAttrList, header);
		
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
        
        System.out.println("RESPONSE BPDY"+responseBody);
        return respMap;
	}

	private Map<String, String> list() {
		
		final String SALESFORCE_LIST_USER_URL = args.get("instance_url")
				+ "/services/data/v25.0/query/?q=SELECT+Username+FROM+User";
		 Map<String,String> header=new HashMap<String,String>();
	        header.put("Authorization", "OAuth "+args.get("access_token"));
	        
	        TransportTools tst=new TransportTools(SALESFORCE_LIST_USER_URL, null, header);
	        String responseBody = null;
	        
	        TransportResponse response = null;
	       
					try {
						response=TransportMachinery.get(tst);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				responseBody=response.entityToString();
			
	      
	        Map<String, String> respMap = new HashMap<>();
	        
	        System.out.println("RESPONSE BPDY"+responseBody);
	        return respMap;

	}

	private void delete() {

	}

	public void update() {

	}
	
	//private class Timezone{
		
		//private Timezone() {
		//	System.out.println("IN TIME ZONE ");
		//}
		
		
	//}
}
