package org.megam.deccanplato.provider.salesforce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.core.RequestDataBuilder;

public class SalesforceAdapterAccess implements AdapterAccess {
    TransportTools tsT;
    RequestData reqData;
	@Override
	public boolean isSuccessful() {
		return false;
	}

	@Override
	public DataMap authenticate(DataMap accessMap) {
		
		String url="https://login.salesforce.com/services/oauth2/token";
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("grant_type", "password"));
        list.add(new BasicNameValuePair("consumer_key", (String) accessMap.map().get("consumer_key")));
        list.add(new BasicNameValuePair("consumer_secret", (String) accessMap.map().get("consumer_secret")));
        list.add(new BasicNameValuePair("access_username", (String) accessMap.map().get("access_username")));
        list.add(new BasicNameValuePair("access_password", (String) accessMap.map().get("access_password")));
        
        tsT=new TransportTools(url, list);
        HttpResponse response;
        String output=null;
        try {
			response=TransportMachinery.post(tsT);
			output=EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       System.out.println("RESPONSE"+output);
        RequestDataBuilder rdb=new RequestDataBuilder(output);
        reqData=rdb.data();
        return (DataMap) reqData;
	}

}
