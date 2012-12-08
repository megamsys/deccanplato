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
package org.megam.deccanplato.provider.zoho.crm.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.zoho.crm.Constants.*;

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
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.zoho.crm.info.Campaigns;
import org.megam.deccanplato.provider.zoho.crm.info.Potentials;

/**
 * 
 * @author pandiyaraja
 * 
 *This class is used to perform Campaign business activity for ZOHO business activities
 * create, list, update and delete. And this class
 *uses a business support classes(Campaigns) to populate ZOHO XML input data.
 */
public class CampaignImpl implements BusinessActivity{

	private static final String ZOHO_CRM_CAMPAIGN_XML_URL="https://crm.zoho.com/crm/private/xml/Campaigns/";
	private static final String ZOHO_CRM_CAMPAIGN_JSON_URL="https://crm.zoho.com/crm/private/json/Campaigns/";
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
		switch (bizInfo.getActivityFunction()) {
		case CREATE:
			outMap=create(outMap);
			break;
		case LIST:
			outMap=list(outMap);
			break;
		case UPDATE:
			outMap=update(outMap);
			break;
		case DELETE:
			outMap=delete(outMap);
			break;
		default:
			break;
		}
		return outMap;
	}

	/**
	 * this method creates a Campaign in zoho.com and returns that Campaign id.
	 * and it uses the business support class Campaigns to populate ZOHO XML input
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		Campaigns campaigns=new Campaigns();
		campaigns.setCampaign_Name(args.get(CAMPAIGN_NAME));
		campaigns.setStart_Date(args.get(START_DATE));
		campaigns.setEnd_Date(args.get(END_DATE));
		campaigns.setStatus(args.get(STATUS));
		campaigns.setExpected_Revenue(args.get(EXPECTED_REVENUE));
		campaigns.setBudgeted_Cost(args.get(BUDGETED_COST));
		campaigns.setActual_Cost(args.get(ACTUAL_COST));
		String xmlout = null;
		try {
			xmlout=campaigns.toXMLString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<NameValuePair> campaignAttrList=new ArrayList<NameValuePair>();
		campaignAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		campaignAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		campaignAttrList.add(new BasicNameValuePair(ZOHO_XMLDATA, xmlout));
		
        TransportTools tst = new TransportTools(ZOHO_CRM_CAMPAIGN_XML_URL+INSERT_RECORDS, campaignAttrList);
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
		return outMap;
	}

	/**
	 * this method lists all Campaign in zoho.com and returns a list of json Campaign details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
		List<NameValuePair> campaignAttrList=new ArrayList<NameValuePair>();
		campaignAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		campaignAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
        
       
        TransportTools tst = new TransportTools(ZOHO_CRM_CAMPAIGN_JSON_URL+GET_RECORDS, campaignAttrList, null, true, "UTF-8");
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
		return outMap;		
	}

	/**
	 * this method update a particular Campaign in zoho.com and returns success message with updated CAMPAIGN id.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		
	Campaigns campaigns=new Campaigns();
	campaigns.setCampaign_Name(args.get(CAMPAIGN_NAME));
	campaigns.setStart_Date(args.get(START_DATE));
	campaigns.setEnd_Date(args.get(END_DATE));
	campaigns.setStatus(args.get(STATUS));
	campaigns.setExpected_Revenue(args.get(EXPECTED_REVENUE));
	campaigns.setBudgeted_Cost(args.get(BUDGETED_COST));
	campaigns.setActual_Cost(args.get(ACTUAL_COST));
	String xmlout = null;
	try {
		xmlout=campaigns.toXMLString();
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		List<NameValuePair> campaignAttrList=new ArrayList<NameValuePair>();
		campaignAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		campaignAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		campaignAttrList.add(new BasicNameValuePair(ID, args.get(ID)));
		campaignAttrList.add(new BasicNameValuePair(ZOHO_XMLDATA, xmlout));
		
		
        TransportTools tst = new TransportTools(ZOHO_CRM_CAMPAIGN_XML_URL+UPDATE_RECORDS, campaignAttrList);
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
		return outMap;
		
	}

	/**
	 * this method delete a particular Campaign in zoho.com and returns success message with deleted Campaign id.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		List<NameValuePair> campaignAttrList=new ArrayList<NameValuePair>();
		campaignAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		campaignAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		campaignAttrList.add(new BasicNameValuePair(ID, args.get(ID)));
		
		TransportTools tst = new TransportTools(ZOHO_CRM_CAMPAIGN_JSON_URL+DELETE_RECORDS, campaignAttrList);
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
		return outMap;		
	}

	/**
     * this method returns business method name to perform action in that zoho crm Module 
     */
	@Override
	public String name() {
		return "campaign";
	}

}
