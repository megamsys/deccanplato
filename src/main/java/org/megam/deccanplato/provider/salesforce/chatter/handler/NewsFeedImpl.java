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

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.salesforce.crm.Constants.*;

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
import org.joda.time.DateTime;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.info.DateTimeTypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author pandiyaraja
 *
 *Newsfeed implements salesforce chatter newsfeed business activity 
 */
public class NewsFeedImpl implements BusinessActivity{


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
		case FEED:
			outMap=feed(outMap);
			break;
		}
		return outMap;
	}

	/**
	 * Returns all feed items from all groups the logged-in user either
	 * owns or is a member of, as well as all files, records, and all
	 * users the current user follows.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> feed(Map<String, String> outMap) {
		
		final String SALESFORCE_CHATTER_ACTIVITY_URL = "/services/data/v26.0/chatter/feeds/news/"+args.get(ID)+"/feed-items";
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)
				+ SALESFORCE_CHATTER_ACTIVITY_URL, null, header);
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
		System.out.println(responseBody);
		return outMap;		
	}

	/**
	 * This class shows news feed url of a user and news feed modified url
	 * it takes input as user id. 
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list(Map<String, String> outMap) {

		final String SALESFORCE_CHATTER_ACTIVITY_URL = "/services/data/v26.0/chatter/feeds/news/"+args.get(ID);
		Map<String, String> header = new HashMap<String, String>();
		header.put(S_AUTHORIZATION, S_OAUTH + args.get(ACCESS_TOKEN));

		TransportTools tst = new TransportTools(args.get(INSTANCE_URL)
				+ SALESFORCE_CHATTER_ACTIVITY_URL, null, header);
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
		System.out.println(responseBody);
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
		return "news";
	}
}
