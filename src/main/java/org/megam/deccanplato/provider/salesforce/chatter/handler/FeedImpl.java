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
package org.megam.deccanplato.provider.salesforce.chatter.handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.megam.deccanplato.provider.salesforce.chatter.Constants.*;
import static org.megam.deccanplato.provider.Constants.*;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.salesforce.chatter.info.Post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author pandiyaraja
 * 
 * This class deals with salesforce chatter functionality of 
 * feed resources. It shows likes, comments and list of user feeds.
 * 
 */
public class FeedImpl implements BusinessActivity {

	private BusinessActivityInfo bizInfo;
	private Map<String, String> args = new HashMap<String, String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.megam.deccanplato.provider.BusinessActivity#setArguments(org.megam
	 * .deccanplato.provider.core.BusinessActivityInfo, java.util.Map)
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.bizInfo = tempBizInfo;
		this.args = tempArgs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.deccanplato.provider.BusinessActivity#run()
	 */
	@Override
	public Map<String, String> run() {
		Map<String, String> outMap = new HashMap<String, String>();
		switch (bizInfo.getActivityFunction()) {
		case LIST:
			outMap = list(outMap);
			break;
		case DELETE:
			outMap=delete(outMap);
			break;
		case COMMENT:
			outMap=comment(outMap);
			break;
		case LIKE:
			outMap=like(outMap);
			break;
		case FEED:
			outMap=feed(outMap);
			break;
		case POSTCOMMENT:
			outMap=postcomment(outMap);
			break;
		}
		return outMap;
	}

	/**
	 * Comment post not working
	 * @param outMap
	 * @return
	 */
	private Map<String, String> postcomment(Map<String, String> outMap) {
		
		
		final String SALESFORCE_CHATTER_URL = args.get(INSTANCE_URL)+"/services/data/v25.0/chatter/feed-items/"+args.get(ID)+"/comments?";
		Map<String,String> header=new HashMap<String,String>();
        header.put(S_AUTHORIZATION, S_OAUTH+args.get(ACCESS_TOKEN));
        Gson gson=new Gson();        
        Map<String, Object> accountAttrMap = new HashMap<String, Object>();
        
        accountAttrMap.put("text", gson.toJson(new Post(args.get(TYPE), args.get(TEXT))));
        
        TransportTools tst=new TransportTools(SALESFORCE_CHATTER_URL, null, header);
        Gson obj = new GsonBuilder().setPrettyPrinting().create();
        tst.setContentType(ContentType.APPLICATION_JSON, obj.toJson(accountAttrMap));
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
	 * This method shows news feeds 
	 * @param outMap
	 * @return
	 */
	private Map<String, String> feed(Map<String, String> outMap) {
		
		final String SALESFORCE_CHATTER_ANSWER_ACTIVITY_URL = "/services/data/v25.0/chatter/feeds/news/"+args.get(ID)+"/feed-items";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)
				+ SALESFORCE_CHATTER_ANSWER_ACTIVITY_URL, null, header);
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
	 * this method shows list of likes of a users comment or post
	 * it takes in put as 
	 * @param outMap
	 * @return
	 */
	private Map<String, String> like(Map<String, String> outMap) {
		
		final String SALESFORCE_CHATTER_ACTIVITY_URL = "/services/data/v25.0/chatter/feed-items/"+args.get(ID)+"/likes";

		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_ACTIVITY_URL,
				null, header);
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
	 * This method shows the comment item details and comment id
	 * it also shows comment count.
	 * @param outMap feed item id
	 * @return
	 */
	private Map<String, String> comment(Map<String, String> outMap) {
		
		final String SALESFORCE_CHATTER_ACTIVITY_URL = "/services/data/v25.0/chatter/feed-items/"+args.get(ID)+"/comments";

		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_ACTIVITY_URL,
				null, header);
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
	 * this method deletes a feed item form user feed
	 * it takes input as feed id
	 * @param outMap
	 * @return
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		final String SALESFORCE_CHATTER_ACTIVITY_URL = "/services/data/v25.0/chatter/feed-items/"+args.get(ID);

		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_ACTIVITY_URL,
				null, header);
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.delete(tst);
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
	 * This method shows list of feed items and access that items
	 * It takes feed id as input.
	 * @param outMap
	 * @return list of user feeds.
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		final String SALESFORCE_CHATTER_ACTIVITY_URL = "/services/data/v25.0/chatter/feed-items/"+args.get(ID);

		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)+SALESFORCE_CHATTER_ACTIVITY_URL,
				null, header);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "feed";
	}

}
