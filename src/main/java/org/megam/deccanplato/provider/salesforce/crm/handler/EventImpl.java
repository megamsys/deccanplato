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
package org.megam.deccanplato.provider.salesforce.crm.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.salesforce.crm.Constants.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.joda.time.DateTime;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.Constants;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.info.DateTimeTypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class implements the business activity of Salesforcecrm event method.
 * this class has four methods, to implement business functions, like create, update,
 * lisd and delete. 
 * 
 * @author pandiyaraja
 */
public class EventImpl implements BusinessActivity{
	private static final String SALESFORCE_EVENT_URL="/services/data/v25.0/sobjects/Event/";
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	private DateTime datetime;
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
 * this method creates an event in salesforce.com and returns that event id.
 * This method gets input from a MAP(contains json data) and returns a MAp.
 * @param outMap 
 */
private Map<String, String> create(Map<String, String> outMap) {
	
final String SALESFORCE_CREATE_EVENT_URL = args.get(INSTANCE_URL)+SALESFORCE_EVENT_URL;
	
	Map<String,String> header=new HashMap<String,String>();
    header.put(S_AUTHORIZATION, S_OAUTH+args.get(ACCESS_TOKEN));
    Map<String, Object> userAttrMap = new HashMap<String, Object>();
    userAttrMap.put("Subject", args.get(SUBJECT));
    userAttrMap.put("StartDateTime", datetime.parse(args.get(START_DATE)));
    userAttrMap.put("EndDateTime", datetime.parse(args.get(END_DATE)));
    
    GsonBuilder gson =new GsonBuilder();
    gson.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
    Gson obj= gson.setPrettyPrinting().create();
    TransportTools tst=new TransportTools(SALESFORCE_CREATE_EVENT_URL, null, header);
    tst.setContentType(ContentType.APPLICATION_JSON, obj.toJson(userAttrMap));
    String responseBody = null;
    
    TransportResponse response = null;
    try {
		response=TransportMachinery.post(tst);
		responseBody=response.entityToString();	
	
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
    
    outMap.put(OUTPUT, responseBody);
	return outMap;		
}

/**
 * this method lists all event in salesforce.com and returns a list of all event details.
 * This method gets input from a MAP(contains json data) and returns a MAp.
 * @param outMap 
 */
private Map<String, String> list(Map<String, String> outMap) {
	final String SALESFORCE_LIST_EVENT_URL = args.get(INSTANCE_URL)
			+ "/services/data/v25.0/query/?q=SELECT+Id,Subject+FROM+Event";
	Map<String, String> header = new HashMap<String, String>();
	header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

	TransportTools tst = new TransportTools(SALESFORCE_LIST_EVENT_URL, null,
			header);
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
 * This method updates a event in salesforce.com and returns a success message with event contract id.
 * This method gets input from a MAP(contains json data) and returns a MAp.
 * @param outMap 
 */
private Map<String, String> update(Map<String, String> outMap) {
final String SALESFORCE_UPDATE_EVENT_URL = args.get(INSTANCE_URL)+SALESFORCE_EVENT_URL+args.get(ID);
	
	Map<String,String> header=new HashMap<String,String>();
    header.put(S_AUTHORIZATION, S_OAUTH+args.get(ACCESS_TOKEN));
    Map<String, Object> userAttrMap = new HashMap<String, Object>();        
    userAttrMap.put("Subject", args.get(EMAIL));
            
    TransportTools tst=new TransportTools(SALESFORCE_UPDATE_EVENT_URL, null, header);
    Gson obj = new GsonBuilder().setPrettyPrinting().create();
    tst.setContentType(ContentType.APPLICATION_JSON, obj.toJson(userAttrMap));
    String responseBody = null;
    try {
		 TransportMachinery.patch(tst);
		 responseBody = UPDATE_STRING+args.get(ID);
	
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
    
    outMap.put(OUTPUT, responseBody);
	return outMap;
}

/**
 * this method deletes an event in salesforce.com and returns a success message with deleted event id.
 * This method gets input from a MAP(contains json data) and returns a MAp.
 * @param outMap 
 */
private Map<String, String> delete(Map<String, String> outMap) {
	final String SALESFORCE_DELETE_EVENT_URL = args.get(INSTANCE_URL)
			+SALESFORCE_EVENT_URL+args.get(ID);
	Map<String, String> header = new HashMap<String, String>();
	header.put(S_AUTHORIZATION, S_OAUTH+ args.get(ACCESS_TOKEN));

	TransportTools tst = new TransportTools(SALESFORCE_DELETE_EVENT_URL, null,
			header);
	String responseBody = null;

	try {
		TransportMachinery.delete(tst);
		responseBody = DELETE_STRING+args.get(ID);
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

/* (non-Javadoc)
 * @see org.megam.deccanplato.provider.BusinessActivity#name()
 */
@Override
public String name() {
	// TODO Auto-generated method stub
	return "event";
}

}
