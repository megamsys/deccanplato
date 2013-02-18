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

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.DateTime;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.info.DateTimeTypeConverter;

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
		case VIEW:
			outMap=view();
			break;
		}
		return outMap;
	}

	/**
	 * @return
	 */
	private Map<String, String> view() {
        Map<String, String> outMap = new HashMap<>();
		
		final String BOX_DOWNLOAD="/files/"+args.get(FILE_ID);
		
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
	private Map<String, String> share() {
		
		Map<String, String> outMap = new HashMap<>();
		final String BOX_UPLOAD="/files/"+args.get(FILE_ID);
		
		Map<String, String> headerMap =new HashMap<String, String>();
		headerMap.put("Authorization", "BoxAuth api_key="+args.get(API_KEY)+"&auth_token="+args.get(TOKEN));
		
		Map<String, String> access=new HashMap<>();
		access.put("access", "open");
		access.put("unshared_at", "2013-02-28T12:07:46.981+05:30");
		Map<String, String> pAccess=new HashMap<>();
		pAccess.put("can_download ", "Company");
		pAccess.put(" can_preview ", "Company");
		Map<String, Map<String, String>> links=new HashMap<>();
		links.put("shared_link", access);
		links.put("permissions", pAccess);
		
		TransportTools tools = new TransportTools(BOX_URI+BOX_UPLOAD, null, headerMap);
		GsonBuilder gson =new GsonBuilder();
	    gson.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
		Gson obj=gson.setPrettyPrinting().create();
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
		headerMap.put("If-Match", args.get(ETAG));
						
		TransportTools tools = new TransportTools(BOX_URI+BOX_UPLOAD, null, headerMap);
				
		String responseBody = "";         
		TransportResponse response = null;
		try {
			response = TransportMachinery.delete(tools);
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
	private Map<String, String> download() {
		System.out.println("File Download");
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
		MultipartEntity entity=new MultipartEntity();
		FileBody filename=new FileBody(new File(args.get(FILE_NAME)));
		FileBody filename1=new FileBody(new File("/home/pandiyaraja/Documents/AMIs"));
		StringBody parent_id = null;
		try {
			parent_id=new StringBody(args.get(FOLDER_ID));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		entity.addPart("filename", filename);
		entity.addPart("parent_id", parent_id);
		TransportTools tools = new TransportTools(BOX_URI+BOX_UPLOAD, null, headerMap);
		tools.setFileEntity(entity);		
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
