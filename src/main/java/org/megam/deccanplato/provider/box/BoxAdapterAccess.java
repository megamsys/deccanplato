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
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultDataMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

/**
 * @author alrin
 * 
 */

public class BoxAdapterAccess implements AdapterAccess {

	private boolean success = false;

	private static String BOX_OAUTH2_URL = "https://www.box.com/api/1.0/rest?action=get_ticket&api_key=";
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
		
		BOX_OAUTH2_URL = BOX_OAUTH2_URL + accessMap.get("api_key");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(responseBody));

		Document doc = null;
		try {
			doc = (Document) db.parse(is);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
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
		}
		
		
	}

}
