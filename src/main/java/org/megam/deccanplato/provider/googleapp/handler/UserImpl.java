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
package org.megam.deccanplato.provider.googleapp.handler;

import static org.megam.deccanplato.provider.Constants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.googleapp.info.AppsForYourDomainClient;

import com.google.gdata.data.ParseSource;
import com.google.gdata.data.appsforyourdomain.provisioning.UserEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.UserFeed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class UserImpl implements BusinessActivity {

	BusinessActivityInfo bizInfo;
	Map<String, String> args = new HashMap<String, String>();
	
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		
		this.bizInfo=tempBizInfo;
		this.args=tempArgs;
	}

	@Override
	public Map<String, String> run() {
		
		Map<String, String> outMap = new HashMap<String, String>();
		System.out.println("USER IMPLEMENTATION METHOD RUN METHOD");
		switch (bizInfo.getActivityFunction()) {
		case CREATE:
			outMap = create(outMap);
			break;
		case LIST:
			outMap = list(outMap);
			break;
		case UPDATE:
			outMap = update(outMap);
			break;
		case DELETE:
			outMap = delete(outMap);
			break;
		default:
			break;
		}

		return outMap;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		AppsForYourDomainClient apclient;
		try {
			apclient = new AppsForYourDomainClient(args.get("email"),
					args.get("password"), args.get("domain"));
			apclient.createUser(args.get("user_name"), args.get("given_name"), args.get("family_name"), args.get("user_password"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		Gson obj = new GsonBuilder().setPrettyPrinting().create();
		Map<String, String> outMap1 =new HashMap<>();
		UserFeed userFeed = null;
		AppsForYourDomainClient apclient;
		try {
			apclient = new AppsForYourDomainClient(args.get("email"),
					args.get("password"), args.get("domain"));
			userFeed = apclient.retrieveAllUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userFeed != null) {
			for (UserEntry userEntry : userFeed.getEntries()) {
				
				outMap.put("user_name", userEntry.getLogin().getUserName());
				outMap.put("user_name", userEntry.getLogin().getPassword());
				//outMap.put("user_name", userEntry.getLogin().getAdmin());
				outMap.put("user_name", userEntry.getName().getFamilyName());
				outMap.put("user_name", userEntry.getName().getGivenName());
				outMap1.putAll(outMap);
			}
               outMap.put(OUTPUT,obj.toJson(outMap1));
		}
         System.out.println("RESULT"+obj.toJson(outMap));
		
		return outMap;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		
		AppsForYourDomainClient apclient;
		try {
			apclient = new AppsForYourDomainClient(args.get("email"),
					args.get("password"), args.get("domain"));
			List<String> list=new ArrayList<>();
			list.add(args.get("user"));
			list.add(args.get("password"));
			UserEntry value=(UserEntry) UserEntry.readEntry((ParseSource) list);
			apclient.updateUser(args.get("user_name"), value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		AppsForYourDomainClient apclient;
		try {
			apclient = new AppsForYourDomainClient(args.get("email"),
					args.get("password"), args.get("domain"));
			apclient.deleteUser(args.get("user_name"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public String name() {
		return "user";
	}

}
