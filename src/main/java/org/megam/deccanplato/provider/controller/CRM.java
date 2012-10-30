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
import org.megam.deccanplato.provider.salesforce.info.Account;
import org.megam.deccanplato.provider.salesforce.info.User;
import org.megam.deccanplato.provider.zoho.crm.AccessParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class CRM extends AdapterHelper {

	private static final String clz = "CRM:";

	private final Logger logger = LoggerFactory.getLogger(CRM.class);
	
	
	public CRM() {
	}

	@RequestMapping(value = "provider/crm", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String getOAuth() {
		logger.info(clz + "getOAuth GET start.");

		String clientId = "3MVG9Y6d_Btp4xp51yG_eZBS13SJirRv1uk0ITwiM5eRcfsC1qg4UinY_FbU3G0niSsUyI0zkEFkhzO89.TmV";
		String clientSecret = "6832915771039819665";

		String salesforce_oauth_url = "https://login.salesforce.com/services/oauth2/token";
		/** For session ID instead of OAuth 2.0, use "grant_type", "password" **/
		String grant_type = "password";
		String username = "pontiyaraja@gmail.com";
		String password = "team4megamE1mlHRMlL7deN0zCN4oDAZlJq";

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
			System.out.println(response.getStatusLine());
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
	String createUser(@RequestBody String inputAsJson)
			throws UnsupportedEncodingException {

		logger.info(clz + "createUser : POST start.\n" + inputAsJson);
		
		/* This is how the new code will work. 
		 * 
		 * RequestDataBuilder rdb = new RequestDataBuilder(inputAsJson);
		RequestData reqdat = rdb.data();
		RequestMediator mediator = mediator(reqdat);
		SendBackResponse respdat = mediator.handleRequest();
		return respdat.toJson();
		*/
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		User salesforceCRM = gson.fromJson(inputAsJson, User.class);
		logger.info(clz + "createUser :" + salesforceCRM.toString());

		String salesforce_create_user_url = salesforceCRM.getInstance_url()

		+ "/services/data/v25.0/sobjects/User/";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(salesforce_create_user_url);

		httpPost.addHeader("Authorization",
				"OAuth " + salesforceCRM.getAccess_token());

		Map<String, Object> userAttrMap = new HashMap<String, Object>();
		userAttrMap.put("Username", salesforceCRM.getUsername());
		userAttrMap.put("FirstName", salesforceCRM.getFirstName());
		userAttrMap.put("Email", salesforceCRM.getEmail());
		userAttrMap.put("Alias", salesforceCRM.getAlias());
		userAttrMap.put("ProfileId", salesforceCRM.getProfileId());
		userAttrMap.put("LastName", salesforceCRM.getLastName());
		userAttrMap.put("TimeZoneSidKey", salesforceCRM.getTimeZoneSidKey());
		userAttrMap.put("LocaleSidKey", salesforceCRM.getLocaleSidKey());
		userAttrMap
				.put("EmailEncodingKey", salesforceCRM.getEmailEncodingKey());
		userAttrMap.put("LanguageLocaleKey",
				salesforceCRM.getLanguageLocaleKey());

		ObjectMapper objmap = new ObjectMapper();

		try {
			httpPost.setEntity(new StringEntity(objmap

			.writeValueAsString(userAttrMap), "application/json", "UTF-8"));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return e.toString();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpPost);
			System.out.println(clz + "createUser : POST : RESPONSE\n"
					+ response);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {

			e.printStackTrace();
			return e.toString();
		} finally {
			httpPost.releaseConnection();
		}

		logger.info(clz + "CreateUser POST end." + output);
		return output;
	}

	@RequestMapping(value = "provider/crm/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String listuser() {
		logger.info(clz + "ListUser : GET start.");

		/**
		 * To-do, when we build an adapter for the system, the access
		 * token/instance_url per request will be memcached
		 * 
		 */
		String instance_url = "https://ap1.salesforce.com";
		String access_token = "00D90000000gFYH!AQoAQBXkWqnqoc6sh5sjQLCl0_AusCzzugyAPJ8l_uq1wICnmPH2zWFYWtsTekxKBY7jP.P.fQ.AgnyrGzh_Zd_AFlHuYsqc";
		String salesforceListSingeUserURL = instance_url
				+ "/services/data/v25.0/query/?q=SELECT+Username+FROM+User";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(salesforceListSingeUserURL);
		httpGet.addHeader("Authorization", "OAuth " + access_token);

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpGet);
			System.out.println(clz + "listUser : GET : RESPONSE\n" + response);
			output = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();

		} finally {
			httpGet.releaseConnection();
		}

		return output;
	}

	@RequestMapping(value = "provider/crm", method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody
	String deleteUser(@RequestBody String access_stuff) {

		logger.info(clz + "deleteUser : DELETE.");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		User salesforceCRM = gson.fromJson(access_stuff,
				User.class);

		logger.info(clz + "deleteUser :" + salesforceCRM.toString());

		String salesforceDeleteSingeUserURL = salesforceCRM.getInstance_url()
				+ "/services/data/v25.0/sobjects/Users/005900000010GuZ";

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
	

	@RequestMapping(value = "provider/crm/account", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	String createSalesforceAccount(@RequestBody String data) {

		logger.info(clz + "createAccount : POST start.\n" + data);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		User salesforceCRM = gson.fromJson(data, User.class);
		Account acc= gson.fromJson(data, Account.class);
		logger.info(clz + "createAccount :" + salesforceCRM.toString());

		String salesforce_create_user_url = salesforceCRM.getInstance_url()

		+ "/services/data/v25.0/sobjects/Account/";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(salesforce_create_user_url);

		httpPost.addHeader("Authorization",
				"OAuth " + salesforceCRM.getAccess_token());

		Map<String, Object> userAttrMap = new HashMap<String, Object>();
		userAttrMap.put("Name", acc.getName());
		ObjectMapper objmap = new ObjectMapper();

		try {
			httpPost.setEntity(new StringEntity(objmap

			.writeValueAsString(userAttrMap), "application/json", "UTF-8"));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return e.toString();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpPost);
			System.out.println(clz + "createAccount : POST : RESPONSE\n"
					+ response);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {

			e.printStackTrace();
			return e.toString();
		} finally {
			httpPost.releaseConnection();
		}

		logger.info(clz + "CreateAccount POST end." + output);
		return output;
	}

	@RequestMapping(value = "provider/crm/account/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String listAccount() {
		logger.info(clz + "ListAccount : GET start.");

		/**
		 * To-do, when we build an adapter for the system, the access
		 * token/instance_url per request will be memcached
		 * 
		 */
		String instance_url = "https://ap1.salesforce.com";
		String access_token = "00D90000000gFYH!AQoAQBXkWqnqoc6sh5sjQLCl0_AusCzzugyAPJ8l_uq1wICnmPH2zWFYWtsTekxKBY7jP.P.fQ.AgnyrGzh_Zd_AFlHuYsqc";
		String salesforceListSingeUserURL = instance_url
				+ "/services/data/v25.0/query/?q=SELECT+Name+FROM+Account";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(salesforceListSingeUserURL);
		httpGet.addHeader("Authorization", "OAuth " + access_token);

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpGet);
			System.out.println(clz + "listAccount : GET : RESPONSE\n" + response);
			output = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();

		} finally {
			httpGet.releaseConnection();
		}

		return output;
	}
	
	@RequestMapping(value = "provider/crm/account", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody
	String deleteAccount() {

		logger.info(clz + "deleteUser : DELETE.");
		/*Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);*/

		//logger.info(clz + "deleteUser2 :" + salesforceCRM.toString());

		String salesforceDeleteSingeUserURL = "https://ap1.salesforce.com"
				+ "/services/data/v25.0/sobjects/Account/0019000000GHm47AAD";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpDelete = new HttpDelete(salesforceDeleteSingeUserURL);
		httpDelete.addHeader("Authorization",
				"OAuth " + "00D90000000gFYH!AQoAQBXkWqnqoc6sh5sjQLCl0_AusCzzugyAPJ8l_uq1wICnmPH2zWFYWtsTekxKBY7jP.P.fQ.AgnyrGzh_Zd_AFlHuYsqc");

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpDelete);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("DELETED USER3:"+output);
		return output;

	}
	
	
	/*===============================ZOHO============================*/
	
	
	@RequestMapping(value = "provider/crm/zoho", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String ZoHoauthentication() {

		logger.info("IN ZOHO OAUTH::::::::::::::::::::::::::");
		String username = "raja.pandiya@megam.co.in";
		String password = "team4megam";
		String OAuth_Url = "https://accounts.zoho.com/apiauthtoken/nb/create?";
		logger.info("IN ZOHO OAUTH::::::::::::::::::::::::::");

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(OAuth_Url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("SCOPE", "ZohoCRM/crmapi"));
		nvps.add(new BasicNameValuePair("EMAIL_ID", username));
		nvps.add(new BasicNameValuePair("PASSWORD", password));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String output = null;

		try {
			HttpResponse response = httpclient.execute(httppost);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			output = gson.toJson(new AccessParser(EntityUtils.toString(response
					.getEntity())));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("ZOHO OAUTH" + output);
		return output;
	}

	@RequestMapping(value = "provider/crm/zoho", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	String getUsers() {

		String user_url = "https://crm.zoho.com/crm/private/json/Users/getUsers?";
		DefaultHttpClient httpclient = new DefaultHttpClient();

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("authtoken",
				"f8166bf9840f2996d619552978ba8e4e"));
		nvps.add(new BasicNameValuePair("scope", "crmapi"));
		nvps.add(new BasicNameValuePair("type", "AllUsers"));
		URI urli = null;
		try {
			urli = URIUtils.createURI("http", "www.crm.zoho.com", -1,
					"crm/private/json/Users/getUsers",
					URLEncodedUtils.format(nvps, "UTF-8"), null);
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HttpGet httpget = new HttpGet(urli);
		String output = "";
		try {
			HttpResponse response = httpclient.execute(httpget);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	@RequestMapping(value = "provider/crm/zoho", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody
	String deletezohouser() {

		logger.info(clz + "deleteUser : DELETE.");
		/*Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);*/

		//logger.info(clz + "deleteUser2 :" + salesforceCRM.toString());

		//String zohoDeleteSingeUserURL = "https://crm.zoho.com/crm/private/json/Users/deleteRecords?";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		//HttpDelete httpDelete = new HttpDelete(zohoDeleteSingeUserURL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("authtoken",
				"d838632f6e22d348aea38b48ab84a632"));
		nvps.add(new BasicNameValuePair("scope", "crmapi"));
		nvps.add(new BasicNameValuePair("id", "660777000000058005"));
		/*httpDelete.addHeader("Authorization",
				"OAuth " + "d838632f6e22d348aea38b48ab84a632");*/
        
		
		
		URI urli = null;
		try {
			urli = URIUtils.createURI("http", "www.crm.zoho.com", -1,
					"crm/private/json/Users/deleteRecords",
					URLEncodedUtils.format(nvps, "UTF-8"), null);
		} catch (URISyntaxException e2) {
			
			e2.printStackTrace();
		}
		HttpDelete httpdel = new HttpDelete(urli);
		
		
		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpdel);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("DELETED USER3:"+output);
		return output;

	}
	
	
	@RequestMapping(value = "provider/crm/zoho/account", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	String createZohoAccount(@RequestBody String data) {

		logger.info(clz + "createAccount : POST start.\n" + data);
		logger.info(clz + "createAccount :");

		String salesforce_create_user_url = "/services/data/v25.0/sobjects/Account/";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(salesforce_create_user_url);

		
		Map<String, Object> userAttrMap = new HashMap<String, Object>();
		userAttrMap.put("Name", "");
		ObjectMapper objmap = new ObjectMapper();

		try {
			httpPost.setEntity(new StringEntity(objmap

			.writeValueAsString(userAttrMap), "application/json", "UTF-8"));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return e.toString();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpPost);
			System.out.println(clz + "createAccount : POST : RESPONSE\n"
					+ response);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {

			e.printStackTrace();
			return e.toString();
		} finally {
			httpPost.releaseConnection();
		}

		logger.info(clz + "CreateAccount POST end." + output);
		return output;
	}
	
	
}
