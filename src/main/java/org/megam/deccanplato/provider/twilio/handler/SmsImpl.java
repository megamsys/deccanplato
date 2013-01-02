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

import static org.megam.deccanplato.provider.twilio.Constants.*;
import static org.megam.deccanplato.provider.Constants.*;

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
 * This class deals with sms implementation of twilio account.
 * this class contains business activities send sms, list sms and view sms.
 */
public class SmsImpl implements BusinessActivity {

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
		case LIST:
			outMap = list(outMap);
			break;
		case SEND:
			outMap=send(outMap);
			break;
		case VIEW:
			outMap=view(outMap);
			break;
		}
		return outMap;
	}

	/**
	 * View method use to view a particular sms details by using sms sid,
	 * From twilio.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> view(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/SMS/Messages/"+args.get(SMS_SID)+".json?";

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
	 * This method use to send a sms to other numbers.
	 * form and to addresses are important to send sms as well as body text.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> send(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/SMS/Messages.json";

		Map<String, String> header = new HashMap<>();
		header.put(PROVIDER, args.get(PROVIDER));
		header.put(ACCOUNT_SID, args.get(ACCOUNT_SID));
		header.put(OAUTH_TOKEN, args.get(OAUTH_TOKEN));
		
		List<NameValuePair> smsList=new ArrayList<>();
		smsList.add(new BasicNameValuePair("From", args.get(FROM)));
		smsList.add(new BasicNameValuePair("To", args.get(TO)));
		smsList.add(new BasicNameValuePair("Body", args.get(BODY)));

		TransportTools tst = new TransportTools(account_view_url, smsList, header);
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
		return outMap;
	}

	/**
	 * This method returns all Sms. and also advance search like by from,
	 * to and date sent fields.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		final String account_view_url = TWILIO_URL + "Accounts/"
				+ args.get(ACCOUNT_SID) + "/SMS/Messages.json?From="
				+ args.get(FROM) + "&To=" + args.get(TO) + "&DateSent="
				+ args.get(DATE_SENT) + "&PageSize=" + args.get(PAGE_SIZE)
				+ "&Page=" + args.get(PAGE);

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
		return "sms";
	}

}
