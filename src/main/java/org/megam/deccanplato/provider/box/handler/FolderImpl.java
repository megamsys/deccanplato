/* 
** Copyright [2012] [Megam Systems]
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
** http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/
package org.megam.deccanplato.provider.box.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.loading.PrivateClassLoader;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.box.Constants.*;

/**
 * @author pandiyaraja
 *
 */
public class FolderImpl implements BusinessActivity{

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#setArguments(org.megam.deccanplato.provider.core.BusinessActivityInfo, java.util.Map)
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.args=tempArgs;
		this.bizInfo=tempBizInfo;		
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#run()
	 */
	@Override
	public Map<String, String> run() {
		Map<String, String> outMap;
		switch(bizInfo.getActivityFunction()) {
		case RETRIVE:
			outMap=retrive();
			break;
		}
		return null;
	}

	/**
	 * @return
	 */
	private Map<String, String> retrive() {
		
		Map<String, String> outMap = new HashMap<>();
		final String BOX_RETRIVE="/folders/"+args.get(FOLDER_ID)+"/items";
		
		Map<String, String> headerMap =new HashMap<String, String>();
		headerMap.put("Authorization", "BoxAuth api_key="+args.get(API_KEY)+"&auth_token="+args.get(TOKEN));
		
		Map<String, String> boxList=new HashMap<>();
		boxList.put("limit", args.get(LIMIT));
		boxList.put("offset", args.get(OFFSET));
		
		TransportTools tools = new TransportTools(BOX_URI+BOX_RETRIVE, null, headerMap);
		Gson obj=new GsonBuilder().setPrettyPrinting().create();
		tools.setContentType(ContentType.APPLICATION_JSON, obj.toJson(boxList));
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

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		return "folder";
	}

}
