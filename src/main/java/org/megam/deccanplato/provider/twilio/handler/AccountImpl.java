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
package org.megam.deccanplato.provider.twilio.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.twilio.Constants.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.mortbay.jetty.HttpURI;

/**
 * @author pandiyaraja
 *
 *Twilio account class implements with user account functionalities such as
 *create a sub account, suspent, activ, close, list, view and list all active
 *accounts like that.
 */
public class AccountImpl implements BusinessActivity{

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#setArguments(org.megam.deccanplato.provider.core.BusinessActivityInfo, java.util.Map)
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;
		
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#run()
	 */
	@Override
	public Map<String, String> run() {
		Map<String, String> outMap = new HashMap<String, String>();
		switch(bizInfo.getActivityFunction()) {
		case VIEW:
			outMap=view(outMap);
			break;
		case CREATE:
			outMap=create(outMap);
			break;
		case LIST:
			outMap=list(outMap);
			break;
		case SUSPEND:
			outMap=suspend(outMap);
			break;
		case ACTIVE:
			outMap=active(outMap);
			break;
		case CLOSE:
			outMap=close(outMap);
			break;
		case LISTACTIVE:
			outMap=listactive(outMap);
			break;
		}
		return null;
	}

	/**
	 * This method lists all active sub-accounts from twilio main account.
	 * 
	 * @param outMap Status of the sub account
	 * @return list of active sub accounts.
	 */
	private Map<String, String> listactive(Map<String, String> outMap) {

		final String account_view_url=TWILIO_URL+"Accounts.json?";
		
        Map<String, String> header=new HashMap<>();
        header.put(PROVIDER, args.get(PROVIDER));
        header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
        header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));
        
		List<NameValuePair> accountList=new ArrayList<NameValuePair>();
		accountList.add(new BasicNameValuePair("Status", args.get(STATUS)));
		
		TransportTools tst = new TransportTools(account_view_url, accountList, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method closed a sub account permanently from a twilio main account
	 * we can't activate an account after that account closed.
	 * @param outMap
	 * @return returns nothing
	 */
	private Map<String, String> close(Map<String, String> outMap) {
        
		final String account_view_url=TWILIO_URL+"Accounts/"+args.get(SUB_ACCOUND_SID)+".json";
		
        Map<String, String> header=new HashMap<>();
        header.put(PROVIDER, args.get(PROVIDER));
        header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
        header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));
        
		List<NameValuePair> accountList=new ArrayList<NameValuePair>();
		accountList.add(new BasicNameValuePair("Status", args.get(STATUS)));
		
		TransportTools tst = new TransportTools(account_view_url, accountList, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.post(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method activates temporarily suspended sub accounts,
	 * by sets status as active of a suspended sub account.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> active(Map<String, String> outMap) {
		
        final String account_view_url=TWILIO_URL+"Accounts/"+args.get(SUB_ACCOUND_SID)+".json";
		
        Map<String, String> header=new HashMap<>();
        header.put(PROVIDER, args.get(PROVIDER));
        header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
        header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));
        
		List<NameValuePair> accountList=new ArrayList<NameValuePair>();
		accountList.add(new BasicNameValuePair("Status", args.get(STATUS)));
		
		TransportTools tst = new TransportTools(account_view_url, accountList, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.post(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method suspend a sub account temporarily by sets the status of an
	 * sub account as suspend. we can activate a suspended account. 
	 * @param outMap
	 * @return
	 */
	private Map<String, String> suspend(Map<String, String> outMap) {
		
        final String account_view_url=TWILIO_URL+"Accounts/"+args.get(SUB_ACCOUND_SID)+".json";
		
        Map<String, String> header=new HashMap<>();
        header.put(PROVIDER, args.get(PROVIDER));
        header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
        header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));
        
		List<NameValuePair> accountList=new ArrayList<NameValuePair>();
		accountList.add(new BasicNameValuePair("Status", args.get(STATUS)));
		
		TransportTools tst = new TransportTools(account_view_url, accountList, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.post(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method lists all the sub accounts with out and condition.
	 * It shows all accounts including closed accounts.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
		final String account_view_url=TWILIO_URL+"Accounts.json";
	       
		 Map<String, String> header=new HashMap<>();
	        header.put(PROVIDER, args.get(PROVIDER));
	        header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
	        header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));
	        
		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		HttpPost post=new HttpPost(account_view_url);		
		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method creates a sub account by only using FriendlyName data.
	 * 
	 * @param outMap
	 * @return
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
        final String account_view_url=TWILIO_URL+"Accounts/.json";
		
        Map<String, String> header=new HashMap<>();
        header.put(PROVIDER, args.get(PROVIDER));
        header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
        header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));
        
		List<NameValuePair> accountList=new ArrayList<NameValuePair>();
		accountList.add(new BasicNameValuePair("FriendlyName", args.get(FRIENDLY_NAME)));
		
		TransportTools tst = new TransportTools(account_view_url, accountList, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.post(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method returns a particular account details, by based on
	 * ACCOUNT_SID key.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> view(Map<String, String> outMap) {
		
		final String account_view_url=TWILIO_URL+"Accounts/"+args.get(ACCOUNT_SID)+".json";
	       
		 Map<String, String> header=new HashMap<String, String>();
	        header.put(PROVIDER, args.get(PROVIDER));
	        header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
	        header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));
	        
		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		HttpPost post=new HttpPost(account_view_url);		
		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "account";
	}

}
