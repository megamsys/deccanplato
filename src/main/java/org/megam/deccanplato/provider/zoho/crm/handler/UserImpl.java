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
package org.megam.deccanplato.provider.zoho.crm.handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

public class UserImpl implements BusinessActivity {

	private static final String CREATE="create";
	private static final String LIST="list";
	private static final String UPDATE="update";
	private static final String DELETE="delete";
	private static final String AUTHTOKEN="OAuth_token";
	private static final String SCOPE="crmapi";
	private static final String TYPE="AllUsers";
	
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		System.out.println("SET ARGS IN USER IMPL"+tempBizInfo+":"+tempArgs.toString());
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;
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
			update();
			break;
		case DELETE:
			delete();
			break;
		default:
			break;
		}
		return null;
	}

	/**
	 * 
	 */
	private Map<String, String> create() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	private Map<String, String> list() {
		
		final String ZOHO_USER_URL = "https://crm.zoho.com/crm/private/json/Users/getUsers?";
		
		System.out.println("AOUTH TOKEN=::::::::::"+args.get(AUTHTOKEN));
		List<NameValuePair> userAttrList=new ArrayList<NameValuePair>();
        userAttrList.add(new BasicNameValuePair("authtoken", args.get(AUTHTOKEN)));
        userAttrList.add(new BasicNameValuePair("scope", SCOPE));
        userAttrList.add(new BasicNameValuePair("type", TYPE));
       
        TransportTools tst = new TransportTools(ZOHO_USER_URL, userAttrList, null, true, "UTF-8");
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
	private Map<String, String> update() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	private Map<String, String> delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name() {
		return "user";
	}

}
