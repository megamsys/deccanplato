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
package org.megam.deccanplato.provider.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.megam.deccanplato.provider.core.CloudMediator;
import org.megam.deccanplato.provider.core.CloudMediatorException;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.core.RequestDataBuilder;
import org.megam.deccanplato.provider.core.SendBackResponse;
import org.megam.deccanplato.provider.salesforce.crm.info.Account;
import org.megam.deccanplato.provider.salesforce.crm.info.User;
import org.megam.deccanplato.provider.zoho.crm.AccessParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class CRM extends Connector {

	private static final String clz = "CRM:";

	private final Logger logger = LoggerFactory.getLogger(CRM.class);

	public CRM() {
	}

	@RequestMapping(value = "provider/crm", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String getOAuth() {
		logger.info(clz + "getOAuth GET start.");

		String clientId = "3MVG9Y6d_Btp4xp4gO.riQJ_rIP8PjQqtO5Hcqdt4NJ99SPjKCd_cBuI_Y4P_WzlNrOvhOLpjEUjpzfskPZ3a";
		String clientSecret = "860088549162819056";

		String salesforce_oauth_url = "https://login.salesforce.com/services/oauth2/token";
		/** For session ID instead of OAuth 2.0, use "grant_type", "password" **/
		String grant_type = "password";
		String username = "nkishore@megam.co.in";
		String password = "walle#1bug74wt3djhr28kLBSKbspj1EOoT";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(salesforce_oauth_url);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("grant_type", grant_type));
		nvps.add(new BasicNameValuePair("client_id", clientId));
		nvps.add(new BasicNameValuePair("client_secret", clientSecret));
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpPost);

			output = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			httpPost.releaseConnection();
		}

		logger.info(clz + "getOAuth GET end." + output);

		return output;
	}

	@RequestMapping(value = "provider/crm", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	String create(@RequestBody String inputAsJson)
			throws UnsupportedEncodingException {

		logger.info(clz + "create : POST start.\n" + inputAsJson);

		// This is how the new code will work.

		RequestDataBuilder rdb = new RequestDataBuilder(inputAsJson);
		RequestData reqdat = rdb.data();
		CloudMediator mediator = mediator(reqdat);
		SendBackResponse respdat = null;
		try {
			respdat = mediator.handleRequest();
			System.out.println("output json-->"+respdat);
		} catch (CloudMediatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respdat.toString();

	}

	@RequestMapping(value = "provider/crm/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String list(@RequestBody String inputAsJson)
			throws UnsupportedEncodingException {

		logger.info(clz + "List : POST start.\n" + inputAsJson);

		// This is how the new code will work.

		RequestDataBuilder rdb = new RequestDataBuilder(inputAsJson);
		RequestData reqdat = rdb.data();
		CloudMediator mediator = mediator(reqdat);
		SendBackResponse respdat = null;
		try {
			respdat = mediator.handleRequest();
			System.out.println("output json-->"+respdat);
		} catch (CloudMediatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respdat.toString();
	}
	
	@RequestMapping(value = "provider/crm", method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody
	String delete(@RequestBody String access_stuff) {

		logger.info(clz + "delete : DELETE.");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		User salesforceCRM = gson.fromJson(access_stuff, User.class);

		logger.info(clz + "delete :" + salesforceCRM.toString());

		String salesforceDeleteSingeUserURL = salesforceCRM.getInstance_url()
				+ "/services/data/v28.0/sobjects/Users/005900000010GuZ";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpDelete = new HttpDelete(salesforceDeleteSingeUserURL);
		httpDelete.addHeader("Authorization",
				"OAuth " + salesforceCRM.getAccess_token());

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpDelete);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;

	}



}
