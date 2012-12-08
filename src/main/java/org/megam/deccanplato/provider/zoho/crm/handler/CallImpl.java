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
import org.megam.deccanplato.provider.zoho.crm.info.Calls;
import org.megam.deccanplato.provider.zoho.crm.info.Events;

/**
 * 
 * @author pandiyaraja
 * 
 *This class is used to perform Call business activity for ZOHO business activities
 * create, list, update and delete. And this class
 *uses a business support classes(Calls) to populate ZOHO XML input data.
 */
public class CallImpl implements BusinessActivity{

	private static final String ZOHO_CRM_CALL_XML_URL="https://crm.zoho.com/crm/private/xml/Calls/";
	private static final String ZOHO_CRM_CALL_JSON_URL="https://crm.zoho.com/crm/private/json/Calls/";
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
		CALL CREATE:
			outMap=create(outMap);
			break;
		CALL LIST:
			outMap=list(outMap);
			break;
		CALL UPDATE:
			outMap=update(outMap);
			break;
		CALL DELETE:
			outMap=delete(outMap);
			break;
		default:
			break;
		}
		return outMap;
	}

	/**
	 * this method creates a call in zoho.com and returns that CALL id.
	 * and it uses the business support class Calls to populate ZOHO XML input
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		Calls calls=new Calls();
		calls.setCall_Duration(args.get(CALL_DURATION));
		calls.setCall_From_To(args.get(CALL_FROM_TO));
		calls.setSubject(args.get(SUBJECT));
		calls.setCall_Start_Time(args.get(CALL_START_TIME));
		calls.setContact_Name(args.get(CONTACT_NAME));	
		calls.setCall_Type(CALL_TYPE);
		String xmlout = null;
		try {
			xmlout=calls.toXMLString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<NameValuePair> callAttrList=new ArrayList<NameValuePair>();
		callAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		callAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		callAttrList.add(new BasicNameValuePair(ZOHO_XMLDATA, xmlout));
		
        TransportTools tst = new TransportTools(ZOHO_CRM_CALL_XML_URL+INSERT_RECORDS, callAttrList);
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
	 * this method lists all calls in zoho.com and returns a list of json calls details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
		
		List<NameValuePair> callAttrList=new ArrayList<NameValuePair>();
		callAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		callAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
        
       
        TransportTools tst = new TransportTools(ZOHO_CRM_CALL_JSON_URL+GET_RECORDS, callAttrList, null, true, "UTF-8");
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
	 * this method update a particular call in zoho.com and returns success message with updated call id.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		
	Calls calls=new Calls();
	calls.setCall_Duration(args.get(CALL_DURATION));
	calls.setCall_From_To(args.get(CALL_FROM_TO));
	calls.setSubject(args.get(SUBJECT));
	calls.setCall_Start_Time(args.get(CALL_START_TIME));
	calls.setContact_Name(args.get(CONTACT_NAME));
	calls.setCall_Type(CALL_TYPE);
	String xmlout = null;
	try {
		xmlout=calls.toXMLString();
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		List<NameValuePair> callAttrList=new ArrayList<NameValuePair>();
		callAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		callAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		callAttrList.add(new BasicNameValuePair(ID, args.get(ID)));
		callAttrList.add(new BasicNameValuePair(ZOHO_XMLDATA, xmlout));
		
		
        TransportTools tst = new TransportTools(ZOHO_CRM_CALL_XML_URL+UPDATE_RECORDS, callAttrList);
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
	 * this method delete a particular call in zoho.com and returns success message with deleted call id.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		List<NameValuePair> callAttrList=new ArrayList<NameValuePair>();
		callAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		callAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		callAttrList.add(new BasicNameValuePair(ID, args.get(ID)));
		
		TransportTools tst = new TransportTools(ZOHO_CRM_CALL_JSON_URL+DELETE_RECORDS, callAttrList);
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
		return "call";
	}
}
