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
 */
public class IncomingNumbersImpl implements BusinessActivity{

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
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
		Map<String, String> outMap=new HashMap<String, String>();
		switch(bizInfo.getActivityFunction()) {
		case LIST:
			outMap=list(outMap);
			break;
		case CREATE:
			outMap=create(outMap);
			break;
		case VIEW:
			outMap=view(outMap);
			break;
		case UPDATE:
			outMap=update(outMap);
			break;
		case DELETE:
			outMap=delete(outMap);
			break;
		}
		return outMap;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/IncomingPhoneNumbers/"+args.get(INCOMING_PHONE_SID)+".json";

		Map<String, String> header = new HashMap<>();
		header.put("provider", args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		TransportTools tst = new TransportTools(account_view_url, null, header);
		String responseBody = null;

		TransportResponse response = null;
		try {
			response = TransportMachinery.delete(tst);
			responseBody = response.entityToString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(responseBody);
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/IncomingPhoneNumbers.json";

		Map<String, String> header = new HashMap<>();
		header.put("provider", args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		List<NameValuePair> incomingList=new ArrayList<NameValuePair>();
		incomingList.add(new BasicNameValuePair("PhoneNumber", args.get(PHONENO)));
		incomingList.add(new BasicNameValuePair("AreaCode", args.get(AREA_CODE)));
		incomingList.add(new BasicNameValuePair("FriendlyName", args.get(FRIENDLY_NAME)));
		incomingList.add(new BasicNameValuePair("ApiVersion", args.get(API_VERSION)));
		incomingList.add(new BasicNameValuePair("VoiceUrl", args.get(VOICE_URL)));
		incomingList.add(new BasicNameValuePair("VoiceMethod", args.get(VOICE_METHOD)));
		incomingList.add(new BasicNameValuePair("VoiceFallbackUrl", args.get(VOICE_FALLBACK_URL)));
		incomingList.add(new BasicNameValuePair("VoiceFallbackMethod", args.get(VOICE_FALLBACK_METHOD)));
		incomingList.add(new BasicNameValuePair("StatusCallback", args.get(STATUS_CALLBACK)));
		incomingList.add(new BasicNameValuePair("StatusCallbackMethod", args.get(STATUS_CALLBACK_METHOD)));
		incomingList.add(new BasicNameValuePair("VoiceCallerIdLookup", args.get(VOICE_CALLERID_LOOKUP)));
		incomingList.add(new BasicNameValuePair("VoiceApplicationSid", args.get(VOICE_APP_SID)));
		incomingList.add(new BasicNameValuePair("SmsUrl", args.get(SMS_URL)));
		incomingList.add(new BasicNameValuePair("SmsMethod", args.get(SMS_METHOD)));
		incomingList.add(new BasicNameValuePair("SmsFallbackUrl", args.get(SMS_FALLBACK_URL)));
		incomingList.add(new BasicNameValuePair("SmsFallbackMethod", args.get(SMS_FALLBACK_METHOD)));
		incomingList.add(new BasicNameValuePair("SmsApplicationSid", args.get(SMS_APP_SID)));

		TransportTools tst = new TransportTools(account_view_url, incomingList, header);
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
		System.out.println(responseBody);
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> view(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/IncomingPhoneNumbers/"+args.get(INCOMING_PHONE_SID)+".json";

		Map<String, String> header = new HashMap<>();
		header.put("provider", args.get(PROVIDER));
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
		System.out.println(responseBody);
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/IncomingPhoneNumbers.json";

		Map<String, String> header = new HashMap<>();
		header.put("provider", args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));

		List<NameValuePair> incomingList=new ArrayList<NameValuePair>();
		incomingList.add(new BasicNameValuePair("PhoneNumber", args.get(PHONENO)));
		incomingList.add(new BasicNameValuePair("AreaCode", args.get(AREA_CODE)));
		incomingList.add(new BasicNameValuePair("FriendlyName", args.get(FRIENDLY_NAME)));
		incomingList.add(new BasicNameValuePair("ApiVersion", args.get(API_VERSION)));
		incomingList.add(new BasicNameValuePair("VoiceUrl", args.get(VOICE_URL)));
		incomingList.add(new BasicNameValuePair("VoiceMethod", args.get(VOICE_METHOD)));
		incomingList.add(new BasicNameValuePair("VoiceFallbackUrl", args.get(VOICE_FALLBACK_URL)));
		incomingList.add(new BasicNameValuePair("VoiceFallbackMethod", args.get(VOICE_FALLBACK_METHOD)));
		incomingList.add(new BasicNameValuePair("StatusCallback", args.get(STATUS_CALLBACK)));
		incomingList.add(new BasicNameValuePair("StatusCallbackMethod", args.get(STATUS_CALLBACK_METHOD)));
		incomingList.add(new BasicNameValuePair("VoiceCallerIdLookup", args.get(VOICE_CALLERID_LOOKUP)));
		incomingList.add(new BasicNameValuePair("VoiceApplicationSid", args.get(VOICE_APP_SID)));
		incomingList.add(new BasicNameValuePair("SmsUrl", args.get(SMS_URL)));
		incomingList.add(new BasicNameValuePair("SmsMethod", args.get(SMS_METHOD)));
		incomingList.add(new BasicNameValuePair("SmsFallbackUrl", args.get(SMS_FALLBACK_URL)));
		incomingList.add(new BasicNameValuePair("SmsFallbackMethod", args.get(SMS_FALLBACK_METHOD)));
		incomingList.add(new BasicNameValuePair("SmsApplicationSid", args.get(SMS_APP_SID)));
		
		TransportTools tst = new TransportTools(account_view_url, incomingList, header);
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
		System.out.println(responseBody);
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/IncomingPhoneNumbers.json";

		Map<String, String> header = new HashMap<>();
		header.put("provider", args.get(PROVIDER));
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
		System.out.println(responseBody);
		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "incomingnumber";
	}

	
}
