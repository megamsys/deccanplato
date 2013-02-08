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
package org.megam.deccanplato.provider.box;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultDataMap;

import static org.megam.deccanplato.provider.box.Constants.*;
import static org.megam.deccanplato.provider.Constants.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author alrin
 * 
 */

public class BoxAdapterAccess implements AdapterAccess {

	private boolean success = false;

	private static final String BOX_OAUTH2_URL = "https://api.box.com/2.0/tokens";
	private String Ticket;

	public BoxAdapterAccess() {
		super();
	}

	@Override
	public boolean isSuccessful() {
		return success;
	}

	@Override
	public <T extends Object> DataMap<T> authenticate(DataMap<T> access)
			throws AdapterAccessException {

		Map<String, T> accessMap = access.map();
		System.out.print("OAUTH MAP"+accessMap.toString());
				
		Map<String, String> headerMap =new HashMap<String, String>();
		headerMap.put("Authorization", "BoxAuth api_key="+(String) accessMap.get(API_KEY));
		
		Map<String, String> boxList=new HashMap<String,String>();
		boxList.put("email", (String) accessMap.get(EMAIL));
		
		TransportTools tools = new TransportTools(BOX_OAUTH2_URL, null, headerMap);
		Gson obj=new GsonBuilder().setPrettyPrinting().create();
		System.out.println(obj.toJson(boxList));
		tools.setContentType(ContentType.APPLICATION_JSON, obj.toJson(boxList));
		String responseBody = null;         
		TransportResponse response = null;
		try {
			response = TransportMachinery.post(tools);
			responseBody = response.entityToString();
			success=true;
			System.out.println("OUTPUT:"+responseBody);
		} catch (ClientProtocolException ce) {
			throw new AdapterAccessException(
					"An error occurred during post operation.", ce);
		} catch (IOException ioe) {
			throw new AdapterAccessException(
					"An error occurred during post operation.", ioe);
		}		
		DataMap<T> accessMap1=new DefaultDataMap<>();
		accessMap1.map().put(OUTPUT, (T) accessMap.get(API_KEY));
		return accessMap1;
		/*
		 * Old Box code written by Thomas Alrin
		 */
		/*BOX_OAUTH2_URL = BOX_OAUTH2_URL + accessMap.get("api_key");
		TransportTools tools = new TransportTools(BOX_OAUTH2_URL, null);
		String responseBody = null;

		TransportResponse response = null;
		try {
			try {
				response = TransportMachinery.get(tools);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			responseBody = response.entityToString();
		} catch (ClientProtocolException ce) {
			throw new AdapterAccessException(
					"An error occurred during post operation.", ce);
		} catch (IOException ioe) {
			throw new AdapterAccessException(
					"An error occurred during post operation.", ioe);
		}
		
		DocumentBuilder db = null;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(responseBody));

		Document doc = null;
		try {
			doc = (Document) db.parse(is);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		NodeList nodes = ((Node) doc).getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node element = nodes.item(i);
			NodeList child = element.getChildNodes();
			for (int j = 0; j < child.getLength(); j++) {
				Node ele = child.item(j);
				if (ele.getNodeName().equals("ticket")) {
					Ticket = ele.getTextContent();
					
				}

			}

		}
		BoxAuthToken((String) accessMap.get("api_key"),Ticket);
		return null;
	}
	
	public void BoxAuthToken(String api, String tick) throws AdapterAccessException {
		String url = "https://www.box.com/api/1.0/rest?action=get_auth_token&api_key="+api+"&ticket="+tick;
		
		TransportTools tools = new TransportTools(url, null);
		String responseBody = null;

		TransportResponse response = null;
		try {
			try {
				response = TransportMachinery.get(tools);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			responseBody = response.entityToString();
		} catch (ClientProtocolException ce) {
			throw new AdapterAccessException(
					"An error occurred during post operation.", ce);
		} catch (IOException ioe) {
			throw new AdapterAccessException(
					"An error occurred during post operation.", ioe);
		}*/
		
		
	}

}
