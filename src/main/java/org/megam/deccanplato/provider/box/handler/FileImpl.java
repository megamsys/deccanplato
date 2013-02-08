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
package org.megam.deccanplato.provider.box.handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.box.Constants.*;

public class FileImpl implements BusinessActivity {
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	@Override
	public Map<String, String> run() {
		Map<String, String> outMap=null;
		switch(bizInfo.getActivityFunction()) {
		case UPLOAD:
			outMap=upload();
			break;
		case DOWNLOAD:
			outMap=download();
			break;
		case DELETE:
			outMap=delete();
			break;
		case SHARE:
			outMap=share();
			break;
		}
		return outMap;
	}

	/**
	 * @return
	 */
	private Map<String, String> share() {
		
		Map<String, String> outMap = new HashMap<>();
		final String BOX_UPLOAD="/files/"+args.get(FILE_ID);
		
		Map<String, String> headerMap =new HashMap<String, String>();
		headerMap.put("Authorization", "BoxAuth api_key="+args.get(API_KEY)+"&auth_token="+args.get(TOKEN));
		
		Map<String, String> access=new HashMap<>();
		access.put("access", "open");
		Map<String, Map<String, String>> links=new HashMap<>();
		links.put("shared_link", access);
		
		TransportTools tools = new TransportTools(BOX_URI+BOX_UPLOAD, null, headerMap);
		Gson obj=new GsonBuilder().setPrettyPrinting().create();
		System.out.println(obj.toJson(links));
		tools.setContentType(ContentType.APPLICATION_JSON, obj.toJson(links));
		String responseBody = "";         
		TransportResponse response = null;
		try {
			response = TransportMachinery.put(tools);
			responseBody = response.entityToString();
			System.out.println("OUTPUT:"+responseBody);
		} catch (ClientProtocolException ce) {
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}	
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * @return
	 */
	private Map<String, String> delete() {
		
		Map<String, String> outMap = new HashMap<>();
		final String BOX_UPLOAD="/files/"+args.get(FILE_ID);
		
		Map<String, String> headerMap =new HashMap<String, String>();
		headerMap.put("Authorization", "BoxAuth api_key="+args.get(API_KEY)+"&auth_token="+args.get(TOKEN));
		headerMap.put("If-Match", args.get(FILE_ID));
						
		TransportTools tools = new TransportTools(BOX_URI+BOX_UPLOAD, null, headerMap);
				
		String responseBody = "";         
		TransportResponse response = null;
		try {
			response = TransportMachinery.delete(tools);
			//responseBody = response.entityToString();
			System.out.println("OUTPUT:"+responseBody);
		} catch (ClientProtocolException ce) {
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}	
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * @return
	 */
	private Map<String, String> download() {
		
		Map<String, String> outMap = new HashMap<>();
		final String BOX_DOWNLOAD="/files/"+args.get(FILE_ID)+"/content";
		
		Map<String, String> headerMap =new HashMap<String, String>();
		headerMap.put("Authorization", "BoxAuth api_key="+args.get(API_KEY)+"&auth_token="+args.get(TOKEN));
		
		TransportTools tools = new TransportTools(BOX_URI+BOX_DOWNLOAD, null, headerMap);
		
		String responseBody = null;         
		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tools);
			responseBody = response.entityToString();
			System.out.println("OUTPUT:"+responseBody);
		} catch (ClientProtocolException ce) {
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}	
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * @return
	 */
	private Map<String, String> upload() {
		Map<String, String> outMap = new HashMap<>();
		final String BOX_UPLOAD="/files/content";
		
		Map<String, String> headerMap =new HashMap<String, String>();
		headerMap.put("Authorization", "BoxAuth api_key="+args.get(API_KEY)+"&auth_token="+args.get(TOKEN));
				
		//Map<String, String> boxList=new HashMap<>();
		//boxList.put("filename", "@"+args.get(FILE_NAME));
		//boxList.put("folder_id", args.get(FOLDER_ID));
		
		List<NameValuePair> boxList= new ArrayList<>();
		boxList.add(new BasicNameValuePair("filename", args.get(FILE_NAME)));
		boxList.add(new BasicNameValuePair("folder_id", args.get(FOLDER_ID)));
		Gson obj=new GsonBuilder().setPrettyPrinting().create();
		
		TransportTools tools = new TransportTools(BOX_URI+BOX_UPLOAD, boxList, headerMap);
		tools.setContentType(ContentType.MULTIPART_FORM_DATA, null);
		
		String responseBody = null;         
		TransportResponse response = null;
		try {
			response = TransportMachinery.post(tools);
			responseBody = response.entityToString();
			System.out.println("OUTPUT:"+responseBody);
		} catch (ClientProtocolException ce) {
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}	
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	@Override
	public String name() {
		return "file";
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#setArguments(org.megam.deccanplato.provider.core.BusinessActivityInfo, java.util.Map)
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.args=tempArgs;
		this.bizInfo=tempBizInfo;
		
	}

}
