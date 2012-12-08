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
import static org.megam.deccanplato.provider.googleapp.Constants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.googleapp.info.AppsForYourDomainClient;

import com.google.gdata.data.ParseSource;
import com.google.gdata.data.appsforyourdomain.provisioning.UserEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.UserFeed;
/**
 * 
 * @author pandiyaraja
 * 
 * This class implements the business activity of GoogleApp user method.
 * this class is implemented by using google-gdata-client library, and this class needs 
 * admin user name and password to get authenticate 
 * this class has four methods, to implement business functions, like create, update,
 * lisd and delete(delete method, delete a user from admin account).
 */

public class UserImpl implements BusinessActivity {
    
	private AppsForYourDomainClient apclient;
	private BusinessActivityInfo bizInfo;
	private Map<String, String> args = new HashMap<String, String>();
	/**
	 * this method initialize the operations to perform (like create, list, update, delete)
	 * client credential set in this method by calling AppsForYourDomainClient class constructor
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		
		this.bizInfo=tempBizInfo;
		this.args=tempArgs;
		try {
			apclient = new AppsForYourDomainClient(args.get(ADMIN_EMAIL),
					args.get(ADMIN_PASSWORD), args.get(DOMAIN));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, String> run() {
		
		Map<String, String> outMap = new HashMap<String, String>();
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
	 * this method creates a user in a domain.
	 * args map has all the value to create a user in a domain,
	 * user account create by calling AppsForYourDomainClient class createUser method 
	 * with client credential apclient
	 * @param outMap
	 * @return outMap has the output
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		
		try {			
			apclient.createUser(args.get(USER_NAME), args.get(GIVEN_NAME), args.get(FAMILY_NAME), args.get(USER_PASSWORD));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       outMap.put(OUTPUT, CREATE_STRING);
		return outMap;
	}

	/**
	 * this method lists all user in a domain.
	 * users list by calling AppsForYourDomainClient class retrieveAllUsers method 
	 * with client credential apclient
	 * @param outMap
	 * @return outMap has the list of users.
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		List<String> list=new ArrayList<String>();
		UserFeed userFeed = null;
		
		try {
			userFeed = apclient.retrieveAllUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userFeed != null) {
			for (UserEntry userEntry : userFeed.getEntries()) {
				
				List<NameValuePair> userlist=new ArrayList<NameValuePair>();
				userlist.add(new BasicNameValuePair(USER_NAME, userEntry.getLogin().getUserName()));
				userlist.add(new BasicNameValuePair(USER_PASSWORD, userEntry.getLogin().getPassword()));
				//outMap.put("user_name", userEntry.getLogin().getAdmin());
				userlist.add(new BasicNameValuePair(FAMILY_NAME, userEntry.getName().getFamilyName()));
				userlist.add(new BasicNameValuePair(GIVEN_NAME, userEntry.getName().getGivenName()));
				list.add(userlist.toString());
				outMap.put(OUTPUT,list.toString());
			}
               
		}
		return outMap;
	}

	/**
	 * this method updates a user in a domain.
	 * args map has all the value to update a user in a domain,
	 * user account updated by calling AppsForYourDomainClient class updateUser method 
	 * with client credential apclient
	 * @param outMap
	 * @return outMap
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		
		try {
			List<String> list=new ArrayList<>();
			list.add(args.get(USER));
			list.add(args.get(PASSWORD));
			UserEntry value=(UserEntry) UserEntry.readEntry((ParseSource) list);
			apclient.updateUser(args.get(USER_NAME), value);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, UPDATE_STRING+args.get("user_name"));
		return outMap;
	}

	/**
	 * this method deletes a user in a domain.
	 * args map has all the value to delete a user in a domain,
	 * user account create by calling AppsForYourDomainClient class deleteUser method 
	 * with client credential apclient
	 * @param outMap
	 * @return outMap
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		try {
			apclient.deleteUser(args.get(USER_NAME));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        outMap.put(OUTPUT, DELETE_STRING+args.get(USER_NAME));
		return outMap;
	}

	@Override
	public String name() {
		return "user";
	}

}
