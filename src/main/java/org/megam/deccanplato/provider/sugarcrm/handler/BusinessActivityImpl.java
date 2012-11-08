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
package org.megam.deccanplato.provider.sugarcrm.handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.Constants;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.sugarcrm.info.MutableSugar;
import org.megam.deccanplato.provider.sugarcrm.info.ImmutableSugar;

import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.sugarcrm.Constants.*;
import com.google.gson.GsonBuilder;
/**
 * 
 * @author pandiyaraja
 * 
 *This class is used to perform common business activity for SugarCRM business activities
 *like create, list, update and delete(delete is not implemented now). And this class
 *uses two business support classes(MutableSugar class and ImmutableSUGAR class).
 */
public class BusinessActivityImpl implements BusinessActivity {

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
    private String name="default";
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {

		this.args = tempArgs;
		this.bizInfo = tempBizInfo;

	}
/**
 * this method redirect to business activity method by switch statement
 * switch statement get input from BusinessActivityInfo class.
 */
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
	 * This method deals with create business activity for users,accounts and leads,
	 * and it takes outMap MAP(contains json data) value as input and returns a MAP 
	 * this method uses MutableSugar class to populate sugarCRM json input.
	 * @param outMap.
	 * 
	 */
	private Map<String, String> create(Map<String, String> outMap) {

		MutableSugar scu = new MutableSugar(name(),args);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(scu);
		System.out.println("USERIMPL JSON VALUE" + json);

		List<NameValuePair> userAttr = new ArrayList<NameValuePair>();
		userAttr.add(new BasicNameValuePair(METHOD, SET_METHOD));
		userAttr.add(new BasicNameValuePair(INPUT_TYPE, TYPE));
		userAttr.add(new BasicNameValuePair(RESPONSE_TYPE, TYPE));
		userAttr.add(new BasicNameValuePair(DATA, json));

		TransportTools tst = new TransportTools(SUGAR_URL, userAttr);
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
		;

		System.out.println("RESPONSE BPDY" + responseBody);
		return outMap;
	}

	/**
	 * This method deals with list business activity for users,accounts and leads,
	 * and it takes outMap MAP(contains json data) value as input and returns a MAP
	 * this method uses ImmutableSugar class to populate sugarCRM json input. 
	 * @param outMap.
	 * 
	 */
	private Map<String, String> list(Map<String, String> outMap) {

		ImmutableSugar scu = new ImmutableSugar(name(), args);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(scu);
		System.out.println("USERIMPL JSON VALUE" + json);
		List<NameValuePair> userAttr = new ArrayList<NameValuePair>();
		userAttr.add(new BasicNameValuePair(METHOD, GET_LIST));
		userAttr.add(new BasicNameValuePair(INPUT_TYPE, TYPE));
		userAttr.add(new BasicNameValuePair(RESPONSE_TYPE, TYPE));
		userAttr.add(new BasicNameValuePair(DATA, json));

		TransportTools tst = new TransportTools(SUGAR_URL, userAttr, null,
				true, "UTF-8");
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.get(tst);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        responseBody=response.entityToString(); 
		outMap.put(OUTPUT, responseBody);
		

		System.out.println("RESPONSE BPDY" + responseBody);
		return outMap;
	}

	/**
	 * This method deals with update business activity for users,accounts and leads,
	 * and it takes outMap MAP(contains json data) value as input and returns a MAP 
	 * this method uses MutableSugar class to populate sugarCRM json input.
	 * @param outMap.
	 * 
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		
		MutableSugar scu = new MutableSugar(name(),args);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(scu);
		
		List<NameValuePair> userAttr = new ArrayList<NameValuePair>();
		userAttr.add(new BasicNameValuePair(METHOD, SET_METHOD));
		userAttr.add(new BasicNameValuePair(INPUT_TYPE, TYPE));
		userAttr.add(new BasicNameValuePair(RESPONSE_TYPE, TYPE));
		userAttr.add(new BasicNameValuePair(DATA, json));

		TransportTools tst = new TransportTools(SUGAR_URL, userAttr);
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
		;

		System.out.println("RESPONSE BPDY" + responseBody);
		return outMap;
		
	}

	/**
	 * This method deals with delete business activity for users,accounts and leads,
	 * and it takes outMap MAP(contains json data) value as input and returns a MAP 
	 * this method uses MutableSugar class to populate sugarCRM json input.
	 * @param outMap.
	 * 
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		return null;
	}	

	@Override
	public String name() {
		return name;
	}
	
	/**
	 * @param activityName
	 */
	public void setName(String activityName) {
		this.name=activityName;
		
	}
	

}
