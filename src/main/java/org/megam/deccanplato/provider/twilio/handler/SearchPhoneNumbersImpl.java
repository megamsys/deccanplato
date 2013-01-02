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
package org.megam.deccanplato.provider.twilio.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.twilio.Constants.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

/**
 * @author pandiyaraja
 * 
 * This class implements search phone numbers business functions in twilio accont
 * this class deals with 2 type of phone numbers that are local number and tollfree numbers. 
 */
public class SearchPhoneNumbersImpl implements BusinessActivity {

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;

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
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;

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
		case LOCALLIST:
			outMap = locallist(outMap);
			break;
		case STARTSWITH:
			outMap = startswith(outMap);
			break;
		case STORM:
			outMap = storm(outMap);
			break;
		case INREGION:
			outMap = region(outMap);
			break;
		case WITHINDISTANCE:
			outMap = withindistance(outMap);
			break;
		case TOLLFREELIST:
			outMap=tollfreelist(outMap);
			break;
		case TOLLFREESTORM:
			outMap=tollfreestorm(outMap);
			break;
		case TOLLFREEPATTERN:
			outMap=tollfreepattern(outMap);
			break;
		}
		return null;
	}

	/**
	 * This method returns available toll free number with pattern by selecting area code
	 * @param outMap
	 * @return
	 */
	private Map<String, String> tollfreepattern(Map<String, String> outMap) {

		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/AvailablePhoneNumbers/"
				+ args.get(COUNTRY_CODE) + "/TollFree.json?AreaCode="+args.get(AREA_CODE)+"&Contains="+args.get(CONTAINS);

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method returns toll free numbers that contains storm.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> tollfreestorm(Map<String, String> outMap) {
		
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/AvailablePhoneNumbers/"
				+ args.get(COUNTRY_CODE) + "/TollFree.json?Contains="+args.get(CONTAINS);

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method returns all toll free numbers in a country. by using
	 * country code.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> tollfreelist(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/AvailablePhoneNumbers/"
				+ args.get(COUNTRY_CODE) + "/TollFree.json";

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method returns available phone numbers within a particular distance and in a region.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> withindistance(Map<String, String> outMap) {

		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/AvailablePhoneNumbers/"
				+ args.get(COUNTRY_CODE) + "/Local.json?Contains="
				+ args.get(CONTAINS) + "&InRegion=" + args.get(INREGION)
				+ "&NearLatLong=" + args.get(NEARLATLONG) + "&Distance="
				+ args.get(DISTANCE);

		List<NameValuePair> phoneList = new ArrayList<NameValuePair>();
		phoneList.add(new BasicNameValuePair("Contains", args.get(CONTAINS)));
		phoneList.add(new BasicNameValuePair("InRegion", args.get(INREGION)));
		phoneList.add(new BasicNameValuePair("NearLatLong", args
				.get(NEARLATLONG)));
		phoneList.add(new BasicNameValuePair("Distance", args.get(DISTANCE)));
		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null,
				header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * this method returns all available phone numbers from a country in a particular region.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> region(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/AvailablePhoneNumbers/"
				+ args.get(COUNTRY_CODE) + "/Local.json?InRegion="
				+ args.get(REGION);

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method returns available phone numbers in a country in that contains storm.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> storm(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/AvailablePhoneNumbers/"
				+ args.get(COUNTRY_CODE) + "/Local.json?Contains="
				+ args.get(CONTAINS);

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method returns available phone numbers in a country that starts with some key.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> startswith(Map<String, String> outMap) {

		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/AvailablePhoneNumbers/"
				+ args.get(COUNTRY_CODE) + "/Local.json?Contains="
				+ args.get(CONTAINS);

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * This method returns all local available phone numbers in a from a country in a particular region.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> locallist(Map<String, String> outMap) {

		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/AvailablePhoneNumbers/"
				+ args.get(COUNTRY_CODE) + "/Local.json?AreaCode="
				+ args.get(AREA_CODE);

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.get(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		return "availablenumbers";
	}

}
