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
 *Thsi user resource class implements user informations like conversation,
 *file, message and status of a user.
 *
 */
public class UserResourcesImpl implements BusinessActivity{

	
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
		Map<String, String> outMap = null;
		switch (bizInfo.getActivityFunction()) {
		case LIST:
			outMap = list();
			break;
		case VIEW:
			outMap = view();
			break;
		case CONVERSATION:
			outMap= conversation();
			break;
		case CONVERSATIONVIEW:
			outMap=conversationview();
			break;
		case FILE:
			outMap=file();
			break;
		case MESSAGE:
			outMap=message();
			break;
		case MESSAGEVIEW:
			outMap=messageview();
			break;
		case STATUS:
			outMap=status();
			break;
			
		}
		return outMap;
	}

	/**
	 * This business method shows status of logged-in user
	 * @param outMap
	 * @return
	 */
	private Map<String, String> status() {
		Map<String, String> outMap=new HashMap<>();
		final String SALESFORCE_CHATTER_CONVERSATION_URL = "/services/data/v26.0/chatter/users/me/status";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_CONVERSATION_URL,
				null, header);
		try {
			String response = TransportMachinery.get(tst).entityToString();
			outMap.put(OUTPUT, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return outMap;
	}

	/**
	 * this business implementation method shows a particular message 
	 * item of logged in user.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> messageview() {
		Map<String, String> outMap=new HashMap<>();
		final String SALESFORCE_CHATTER_CONVERSATION_URL = "/services/data/v25.0/chatter/users/me/messages/"+args.get(ID);
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_CONVERSATION_URL,
				null, header);
		try {
			String response = TransportMachinery.get(tst).entityToString();
			outMap.put(OUTPUT, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return outMap;
	}

	/**
	 * This method shows message list of logged-in user
	 * @param outMap
	 * @return
	 */
	private Map<String, String> message() {
		Map<String, String> outMap=new HashMap<>();
		final String SALESFORCE_CHATTER_CONVERSATION_URL = "/services/data/v25.0/chatter/users/me/messages";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_CONVERSATION_URL,
				null, header);
		try {
			String response = TransportMachinery.get(tst).entityToString();
			outMap.put(OUTPUT, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return outMap;
	}

	/**
	 * This business method returns list of files of logged in user
	 * @param outMap
	 * @return
	 */
	private Map<String, String> file() {
		Map<String, String> outMap=new HashMap<>();
		final String SALESFORCE_CHATTER_CONVERSATION_URL = "/services/data/v25.0/chatter/users/me/files";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_CONVERSATION_URL,
				null, header);
		try {
			String response = TransportMachinery.get(tst).entityToString();
			outMap.put(OUTPUT, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return outMap;
	}

	/**
	 * This business method returns particular conversation item of logged in user
	 * @param outMap
	 * @return
	 */
	private Map<String, String> conversationview() {
		Map<String, String> outMap=new HashMap<>();
		final String SALESFORCE_CHATTER_CONVERSATION_URL = "/services/data/v25.0/chatter/users/me/conversations/"+args.get(ID);
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_CONVERSATION_URL,
				null, header);
		try {
			String response = TransportMachinery.get(tst).entityToString();
			outMap.put(OUTPUT, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return outMap;
	}

	/**
	 * This business method returns list of conversations of logged in user
	 * @param outMap
	 * @return
	 */
	private Map<String, String> conversation() {
		Map<String, String> outMap=new HashMap<>();
		final String SALESFORCE_CHATTER_CONVERSATION_URL = "/services/data/v25.0/chatter/users/me/conversations";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_CONVERSATION_URL,
				null, header);

		try {
			String response = TransportMachinery.get(tst).entityToString();
			outMap.put(OUTPUT, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return outMap;
	}

	/**
	 * This business method returns information about the logged in user's profile
	 * @param outMap
	 * @return
	 */
	private Map<String, String> view() {
		Map<String, String> outMap=new HashMap<>();
		final String SALESFORCE_CHATTER_CONVERSATION_URL = "/services/data/v25.0/chatter/users/me";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_CONVERSATION_URL,
				null, header);
		try {
			String response = TransportMachinery.get(tst).entityToString();
			outMap.put(OUTPUT, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return outMap;
	}

	/**
	 * This business method returns information about all users in an oganization.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list() {
		Map<String, String> outMap=new HashMap<>();
		final String SALESFORCE_CHATTER_CONVERSATION_URL = "/services/data/v25.0/chatter/users";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_CONVERSATION_URL,
				null, header);
		try {
			String response = TransportMachinery.get(tst).entityToString();
			outMap.put(OUTPUT, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return outMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		return "user";
	}

}
