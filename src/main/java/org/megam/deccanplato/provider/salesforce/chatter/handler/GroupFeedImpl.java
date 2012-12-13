/**
 * “Copyright 2012 Megam Systems”
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.megam.deccanplato.provider.salesforce.chatter.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.salesforce.crm.Constants.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

/**
 * @author pandiyaraja
 * 
 * This class deals with salesforce chatter activity groupfeed resources
 * this class lists all group in an organization and describes a particular group
 * it delete a group membership and also describes members from that group then lists 
 * all files in that group
 */
public class GroupFeedImpl implements BusinessActivity {

	private static final String SALESFORCECRM_CHATTER_MESSAGE_URL = "/services/data/v25.0/chatter/groups/";
	private BusinessActivityInfo bizInfo;
	private Map<String, String> args = new HashMap<String, String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.megam.deccanplato.provider.BusinessActivity#setArguments(org.megam
	 * .deccanplato.provider.core.BusinessActivityInfo, java.util.Map)
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.bizInfo = tempBizInfo;
		this.args = tempArgs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.deccanplato.provider.BusinessActivity#run()
	 */
	@Override
	public Map<String, String> run() {
		Map<String, String> outMap = new HashMap<String, String>();
		switch (bizInfo.getActivityFunction()) {
		case LIST:
			outMap = list(outMap);
			break;
		case MEMBERSHIP:
			outMap=membership(outMap);
			break;
		case DELETE:
			outMap = delete(outMap);
			break;
		case VIEW:
			outMap=view(outMap);
			break;
		case FILE:
			outMap=file(outMap);
			break;
		case MEMBER:
			outMap=member(outMap);
			break;
		}
		return outMap;
	}

	/**
	 * This method takes input as group id and returns all grooup members 
	 * from that group. 
	 * @param outMap(group id)
	 * @return list of members from that group
	 */
	private Map<String, String> member(Map<String, String> outMap) {
		
		final String SALESFORCECRM_CHATTER_URL = "/services/data/v25.0/chatter/groups/"+args.get(ID)+"/members";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)
				+ SALESFORCECRM_CHATTER_URL, null, header);
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
		System.out.println(responseBody);
		return outMap;
	}

	/**
	 * this method takes input as group id and returns list of 
	 * files from that group. group id is taken from group membership subscription
	 * @param outMap(user group id)
	 * @return list of files from that group
	 */
	private Map<String, String> file(Map<String, String> outMap) {
		final String SALESFORCECRM_CHATTER_URL = "/services/data/v25.0/chatter/groups/"+args.get(ID)+"/files";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)
				+ SALESFORCECRM_CHATTER_URL, null, header);
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
		System.out.println(responseBody);
		return outMap;
	}

	/**
	 * this method returns a particular group details
	 * it takes group id from group membership method implementation
	 * @param outMap(Group id)
	 * @return details about a group
	 */
	private Map<String, String> view(Map<String, String> outMap) {  
		
	Map<String, String> header = new HashMap<String, String>();
	header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

	TransportTools tst = new TransportTools(args.get(INSTANCE_URL)
			+ SALESFORCECRM_CHATTER_MESSAGE_URL+args.get(ID), null, header);
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
	System.out.println(responseBody);
	return outMap;
	
	}

	/**
	 * this method returns membership id from a group
	 * it takes group id as argument
	 * @param outMap
	 * @return returns membership details
	 */
	private Map<String, String> membership(Map<String, String> outMap) {
		
		final String SALESFORCECRM_CHATTER_URL = "/services/data/v25.0/chatter/group-memberships/"+args.get(ID);
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)
				+ SALESFORCECRM_CHATTER_URL, null, header);
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
		System.out.println(responseBody);
		return outMap;
	}

	/**
	 * this method delete a member from a group membership
	 * it takes input as membership id.
	 * @param outMap membership id.
	 * @return
	 */
	private Map<String, String> delete(Map<String, String> outMap) {

		final String SALESFORCECRM_CHATTER_URL = "/services/data/v25.0/chatter/group-memberships/"+args.get(ID);
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)
				+ SALESFORCECRM_CHATTER_URL, null,
				header);
		String responseBody = null;

		try {
			TransportMachinery.delete(tst);
			responseBody = DELETE_STRING + args.get(ID);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method lists all groups in an organization
	 * @param outMap
	 * @return returns group id and group details.
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)
				+ SALESFORCECRM_CHATTER_MESSAGE_URL, null, header);
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
		System.out.println(responseBody);
		return outMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "groupfeed";
	}
}
