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
import org.megam.deccanplato.provider.zoho.crm.info.Leads;
import org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.zoho.crm.Constants.*;
import static org.megam.deccanplato.provider.Constants.*;
/**
 * 
 * @author pandiyaraja
 * 
 *This class is used to perform Leads business activity for ZOHO business activities
 * create, list, update and delete(delete is not implemented now). And this class
 *uses a business support classes(Accounts) to populate ZOHO XML input data.
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
	 * This method creates a lead in zoho.com and returns that account id.
	 * and it uses the business support class Leads to populate ZOHO XML input.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
        final String ZOHO_LEADS_CREATE_URL = "https://crm.zoho.com/crm/private/xml/Leads/insertRecords?";
		
		Leads leads=new Leads();
		leads.setAnualRevenue(args.get(ANNUALREVENUE));
		leads.setCity(args.get(CITY));
		leads.setCompany(args.get(COMPANY));
		leads.setCountry(args.get(COUNTRY));
		leads.setDescription(args.get(DESCRIPTION));
		leads.setDesignation(args.get(DESIGNATION));
		leads.setEmail(args.get(EMAIL));
		leads.setEmailOptOut(args.get(EMAIL_OPT_OUT));
		leads.setFax(args.get(FAX));
		leads.setFirstname(args.get(FIRSTNAME));
		leads.setIndustry(args.get(INDUSTRY));
		leads.setLastname(args.get(LASTNAME));
		leads.setLeadSource(args.get(LEAD_SOURCE));
		leads.setLeadStatus(args.get(LEAD_STATUS));
		leads.setMobile(args.get(MOBILE));
		leads.setNoOfEmployees(args.get(EMPLOYEES));
		leads.setPhone(args.get(PHONE));
		leads.setSalutation(args.get(SALUTATION));
		leads.setSkypeId(args.get(SKYPE_ID));
		leads.setState(args.get(STATE));
		leads.setStreet(args.get(STREET));
		leads.setWebsite(args.get(WEBSITE));
		leads.setZipCode(args.get(ZIP_CODE));
		String xmlout = null;
		try {
			xmlout=leads.toXMLString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("XML STRING VALUE"+xmlout);
		List<NameValuePair> accountAttrList=new ArrayList<NameValuePair>();
		accountAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		accountAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		accountAttrList.add(new BasicNameValuePair(ZOHO_XMLDATA, xmlout));
		
        TransportTools tst = new TransportTools(ZOHO_LEADS_CREATE_URL, accountAttrList);
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
		responseBody = response.statusLineToString();

		outMap.put(OUTPUT, responseBody);

		System.out.println("RESPONSE BPDY" + responseBody);
		return outMap;
	}

	/**
	 * this method list all leads in zoho.com and returns a list of leads details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
       final String ZOHO_LEAD_LIST_URL = "https://crm.zoho.com/crm/private/json/Leads/getRecords";
		
		
		List<NameValuePair> accountAttrList=new ArrayList<NameValuePair>();
		accountAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		accountAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
        
       
        TransportTools tst = new TransportTools(ZOHO_LEAD_LIST_URL, accountAttrList, null, true, "UTF-8");
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
	 * This method update a lead in zoho.com and returns a success message with updated lead id.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method delete a particular lead in zoho.com and returns a Success message with deleted account id.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
final String ZOHO_LEAD_DELETE_URL="https://crm.zoho.com/crm/private/json/Leads/deleteRecords";
		
		List<NameValuePair> accountAttrList=new ArrayList<NameValuePair>();
		accountAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		accountAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		accountAttrList.add(new BasicNameValuePair(ID, args.get(ID)));
		
		TransportTools tst = new TransportTools(ZOHO_LEAD_DELETE_URL, accountAttrList);
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
		return "lead";
	}

}
