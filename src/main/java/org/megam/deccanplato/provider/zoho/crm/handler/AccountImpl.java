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

import javax.xml.bind.JAXBException;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.Constants;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.zoho.crm.info.Accounts;
import static org.megam.deccanplato.provider.zoho.crm.Constants.*;
import static org.megam.deccanplato.provider.Constants.*;

/**
 * 
 * @author pandiyaraja
 * 
 *This class is used to perform Account business activity for ZOHO business activities
 * create, list, update and delete(delete is not implemented now). And this class
 *uses a business support classes(Accounts) to populate ZOHO XML input data.
 */
public class AccountImpl implements BusinessActivity {

		
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
	 * this method creates an account in zoho.com and returns that account id.
	 * and it uses the business support class Accounts to populate ZOHO XML input
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		final String ZOHO_ACCOUNT_CREATE_URL = "https://crm.zoho.com/crm/private/xml/Accounts/insertRecords?";
		
		Accounts accounts=new Accounts();
		accounts.setAccount_Name(args.get(ACCOUNTNAME));
		//accounts.setAccount_owner(args.get(ACCOUNTOWNER));
		accounts.setAnual_Revenue(args.get(ANNUALREVENUE));
		accounts.setEmployees(args.get(EMPLOYEES));
		accounts.setFax(args.get(FAX));
		accounts.setPhone(args.get(PHONE));
		String xmlout = null;
		try {
			xmlout=accounts.toXMLString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("XML STRING VALUE"+xmlout);
		List<NameValuePair> accountAttrList=new ArrayList<NameValuePair>();
		accountAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		accountAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		accountAttrList.add(new BasicNameValuePair(ZOHO_XMLDATA, xmlout));
		
        TransportTools tst = new TransportTools(ZOHO_ACCOUNT_CREATE_URL, accountAttrList);
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.post(tst);
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
	 * this method lists all account in zoho.com and returns a list of json account details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
       final String ZOHO_ACCOUNT_LIST_URL = "https://crm.zoho.com/crm/private/json/Accounts/getRecords";
		
		
		List<NameValuePair> accountAttrList=new ArrayList<NameValuePair>();
		accountAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		accountAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
        
       
        TransportTools tst = new TransportTools(ZOHO_ACCOUNT_LIST_URL, accountAttrList, null, true, "UTF-8");
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

		System.out.println("RESPONSE BPDY LIST" + responseBody);
		return outMap;		
	}

	/**
	 * this method update a particular account in zoho.com and returns success message with updated account id.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		

final String ZOHO_ACCOUNT_UPDATE_URL = "https://crm.zoho.com/crm/private/xml/Accounts/updateRecords?";
		
		Accounts accounts=new Accounts();
		accounts.setAccount_Name(args.get(ACCOUNTNAME));
		//accounts.setAccount_owner(args.get(ACCOUNTOWNER));
		accounts.setAnual_Revenue(args.get(ANNUALREVENUE));
		accounts.setEmployees(args.get(EMPLOYEES));
		accounts.setFax(args.get(FAX));
		accounts.setPhone(args.get(PHONE));
		String xmlout = null;
		try {
			xmlout=accounts.toXMLString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("XML STRING VALUE"+xmlout);
		List<NameValuePair> accountAttrList=new ArrayList<NameValuePair>();
		accountAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		accountAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		accountAttrList.add(new BasicNameValuePair(ID, args.get(ID)));
		accountAttrList.add(new BasicNameValuePair(ZOHO_XMLDATA, xmlout));
		
		
        TransportTools tst = new TransportTools(ZOHO_ACCOUNT_UPDATE_URL, accountAttrList);
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.put(tst);
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
	 * this method delete a particular account in zoho.com and returns success message with deleted account id.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		final String ZOHO_ACCOUNT_DELETE_URL="https://crm.zoho.com/crm/private/json/Accounts/deleteRecords";
		
		List<NameValuePair> accountAttrList=new ArrayList<NameValuePair>();
		accountAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		accountAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		accountAttrList.add(new BasicNameValuePair(ID, args.get(ID)));
		
		TransportTools tst = new TransportTools(ZOHO_ACCOUNT_DELETE_URL, accountAttrList);
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.post(tst);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseBody = response.entityToString();

		outMap.put(OUTPUT, responseBody);

		System.out.println("RESPONSE BPDY LIST" + responseBody);
		return outMap;		
	}

	/**
     * this method returns business method name to perform action in that Salesforce Module 
     */
	@Override
	public String name() {
		return "account";
	}

}
