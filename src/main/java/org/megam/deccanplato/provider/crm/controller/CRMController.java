package org.megam.deccanplato.provider.crm.controller;

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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.megam.deccanplato.provider.salesforce.info.SalesforceCRM;
import org.megam.deccanplato.provider.salesforce.info.SalesforceCRMLead;
import org.megam.deccanplato.provider.salesforce.info.SalesforceCrmAccount;
import org.megam.deccanplato.provider.sugarcrm.info.SugarAccountCreate;
import org.megam.deccanplato.provider.sugarcrm.info.SugarAccountUpdate;
import org.megam.deccanplato.provider.sugarcrm.info.SugarCreateUser;
import org.megam.deccanplato.provider.sugarcrm.info.SugarLeadCreate;
import org.megam.deccanplato.provider.sugarcrm.info.SugarUser;
import org.megam.deccanplato.provider.sugarcrm.info.SugarleadUpdate;
import org.megam.deccanplato.provider.sugarcrm.info.Sugarrelation;
import org.megam.deccanplato.provider.sugarcrm.info.SugaruserList;
import org.megam.deccanplato.provider.zoho.crm.info.ZoHoCRM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class CRMController {

	private static final String clz = "CRMController:";

	
	private final Logger logger = LoggerFactory.getLogger(CRMController.class);

	public CRMController() {
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
	String createUser(@RequestBody String access_stuff)
			throws UnsupportedEncodingException {

		logger.info(clz + "createUser : POST start.\n" + access_stuff);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff, SalesforceCRM.class);
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
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);

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
		SalesforceCRM salesforceCRM = gson.fromJson(data, SalesforceCRM.class);
		SalesforceCrmAccount acc= gson.fromJson(data, SalesforceCrmAccount.class);
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
        System.out.println("DELETED ACCOUNT END3:"+output);
		return output;

	}
	@RequestMapping(value = "provider/crm/leads", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	String createSalesforceLead(@RequestBody String data) {

		logger.info(clz + "createLead : POST start.\n" + data);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SalesforceCRM salesforceCRM = gson.fromJson(data, SalesforceCRM.class);
		SalesforceCRMLead lead= gson.fromJson(data, SalesforceCRMLead.class);
		logger.info(clz + "createLead :" + salesforceCRM.toString());

		String salesforce_create_user_url = salesforceCRM.getInstance_url()+ "/services/data/v25.0/sobjects/Lead/";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(salesforce_create_user_url);

		httpPost.addHeader("Authorization",
				"OAuth " + salesforceCRM.getAccess_token());

		Map<String, Object> userAttrMap = new HashMap<String, Object>();
		userAttrMap.put("Lastname","Raja");
		userAttrMap.put("Company", "Megam");
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
			System.out.println(clz + "createLead : POST : RESPONSE\n"
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

		logger.info(clz + "CreateLead POST end." + output);
		return output;
	}

	@RequestMapping(value = "provider/crm/leads", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String listSalesforceLead() {
		logger.info(clz + "ListLead : GET start.");

		/**
		 * To-do, when we build an adapter for the system, the access
		 * token/instance_url per request will be memcached
		 * 
		 */
		String instance_url = "https://ap1.salesforce.com";
		String access_token = "00D90000000gFYH!AQoAQPUmWc4yy_G056_qZWqaFYCZ2XLnUXmKy2VjytQFjQR3UqhCCJju1keqQm_tYb_FS4nIeS.AyBdn2NMz9JKNugLwxcp6";
		String salesforceListSingeUserURL = instance_url
				+ "/services/data/v25.0/query/?q=SELECT+Name+FROM+Lead";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(salesforceListSingeUserURL);
		httpget.addHeader("Authorization", "OAuth " + access_token);

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpget);
			System.out.println(clz + "listLead : GET : RESPONSE\n" + response);
			output = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();

		} finally {
			httpget.releaseConnection();
		}

		return output;
	}
	
	@RequestMapping(value = "provider/crm/leads", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody
	String deleteSalesforceLead() {

		logger.info(clz + "deleteUser : DELETE.");
		/*Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);*/

		//logger.info(clz + "deleteUser2 :" + salesforceCRM.toString());

		String salesforceDeleteSingeUserURL = "https://ap1.salesforce.com"
				+ "/services/data/v25.0/sobjects/Lead/00Q9000000AMNogEAH";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpdelete = new HttpDelete(salesforceDeleteSingeUserURL);
		httpdelete.addHeader("Authorization",
				"OAuth " + "00D90000000gFYH!AQoAQPUmWc4yy_G056_qZWqaFYCZ2XLnUXmKy2VjytQFjQR3UqhCCJju1keqQm_tYb_FS4nIeS.AyBdn2NMz9JKNugLwxcp6");

		String output = null;
		try {
			HttpResponse response = httpclient.execute(httpdelete);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("DELETED Lead END3:"+output);
		return output;

	}
	
	
	/*****************************************************************/
	/*===============================ZOHO============================*/
	/*****************************************************************/	
	
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
			output = gson.toJson(new ZoHoCRM(EntityUtils.toString(response
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

		String zohoDeleteSingeUserURL = "https://crm.zoho.com/crm/private/json/Users/deleteRecords?";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(zohoDeleteSingeUserURL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("authtoken", "01b08320a84ba27efa0132dd3bc16edf"));
		nvps.add(new BasicNameValuePair("scope", "crmapi"));
		nvps.add(new BasicNameValuePair("id", "660777000000058005"));
		
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String output = null;
		try {
			HttpResponse response = httpclient.execute(httppost);
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("DELETED USER3:"+output);
		return output;

	}
	
	
	@RequestMapping(value = "provider/crm/zoho/leads", method = RequestMethod.POST)
	public @ResponseBody
	String createZohoLeads(@RequestBody String data) {

		logger.info(clz + "createLEAD : POST start.\n" + data);
		logger.info(clz + "createLEAD :");

		String zoho_create_user_url = "https://crm.zoho.com/crm/private/xml/Leads/insertRecords";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(zoho_create_user_url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("authtoken",
				"d838632f6e22d348aea38b48ab84a632"));
		nvps.add(new BasicNameValuePair("scope", "crmapi"));
		nvps.add(new BasicNameValuePair("xmlData", data));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String output = null;
		try {
			HttpResponse response = httpclient.execute(httppost);
			logger.info(""+response.getStatusLine()+"/n"+response.toString());
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        

		logger.info(clz + "CreateLEADS POST end." + output);
		return output;
	}
	
	
	@RequestMapping(value = "provider/crm/zoho/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String ZohoLeadList() {

		logger.info("IN ZOHO List::::::::::::::::::::::::::");
		
		String OAuth_Url = "https://crm.zoho.com/crm/private/json/Leads/getMyRecords?";
		logger.info("IN ZOHO List::::::::::::::::::::::::::");

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(OAuth_Url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		//nvps.add(new BasicNameValuePair("newFormat", "1"));
		nvps.add(new BasicNameValuePair("authtoken", "d838632f6e22d348aea38b48ab84a632"));
		nvps.add(new BasicNameValuePair("scope", "crmapi"));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String output = null;

		try {
			HttpResponse response = httpclient.execute(httppost);
			output=EntityUtils.toString(response
					.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("ZOHO List" + output);
		return output;
	}
	
	@RequestMapping(value = "provider/crm/zoho/accounts", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String ZohoAccountList() {

		logger.info("IN ZOHO List::::::::::::::::::::::::::");
		
		String OAuth_Url = "https://crm.zoho.com/crm/private/json/Accounts/getRecords?";
		logger.info("IN ZOHO List::::::::::::::::::::::::::");

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(OAuth_Url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		//nvps.add(new BasicNameValuePair("newFormat", "1"));
		nvps.add(new BasicNameValuePair("authtoken", "d838632f6e22d348aea38b48ab84a632"));
		nvps.add(new BasicNameValuePair("scope", "crmapi"));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String output = null;

		try {
			HttpResponse response = httpclient.execute(httppost);
			output=EntityUtils.toString(response
					.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("ZOHO List" + output);
		return output;
	}
	
	@RequestMapping(value = "provider/crm/zoho/leads", method = RequestMethod.DELETE)
	public @ResponseBody
	String ZohoLeadtDelete() {

		logger.info("IN ZOHO Delete Lead::::::::::::::::::::::::::");
		
		String OAuth_Url = "https://crm.zoho.com/crm/private/json/Accounts/deleteRecords?";
		logger.info("IN ZOHO Lead Delete::::::::::::::::::::::::::");

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(OAuth_Url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("authtoken", "01b08320a84ba27efa0132dd3bc16edf"));
		nvps.add(new BasicNameValuePair("scope", "crmapi"));
		nvps.add(new BasicNameValuePair("id", "660777000000059005"));
        try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String output = null;

		try {
			HttpResponse response = httpclient.execute(httppost);
			output=EntityUtils.toString(response.getEntity());
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("ZOHO Delete Leat" + output);
		return output;
	}
	
	@RequestMapping(value = "provider/crm/zoho/accounts", method = RequestMethod.POST)
	public @ResponseBody
	String createZohoAccount(@RequestBody String data) {

		logger.info(clz + "createLEAD : POST start.\n" + data);
		logger.info(clz + "createLEAD :");

		String zoho_create_user_url = "https://crm.zoho.com/crm/private/xml/Accounts/insertRecords";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(zoho_create_user_url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("authtoken",
				"d838632f6e22d348aea38b48ab84a632"));
		nvps.add(new BasicNameValuePair("scope", "crmapi"));
		nvps.add(new BasicNameValuePair("xmlData", data));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String output = null;
		try {
			HttpResponse response = httpclient.execute(httppost);
			logger.info(""+response.getStatusLine()+"/n"+response.toString());
			output = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        

		logger.info(clz + "CreateLEADS POST end." + output);
		return output;
	}
	
	/**************************************/
	/*============SugarCRM================*/
	/**************************************/
	
	
	@RequestMapping(value="provider/crm/sugar",method=RequestMethod.GET)
	public @ResponseBody String loginSugar(){
		
		SugarUser su=new SugarUser();
		Gson gson=new Gson();
		String json=gson.toJson(su);
		
		
		logger.info("JSON DATA"+json);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://localhost/sugarcrm/service/v4/rest.php");
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("method", "login"));
		nvps.add(new BasicNameValuePair("input_type", "JSON"));
		nvps.add(new BasicNameValuePair("response_type", "JSON"));
		nvps.add(new BasicNameValuePair("rest_data", json));
		
	    try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
	   String Sessionid;
	    String respBody = null;
	    try{
	         JSONObject Json=null;
	        HttpResponse response= httpclient.execute(httppost);
	         respBody = EntityUtils.toString(response.getEntity());
	         logger.info("RESPONSE BODY"+respBody);
	         try {
				Json=new JSONObject(respBody);
				Sessionid=Json.getString("id");
				logger.info("SESSION ID:::::"+Sessionid);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }
	    logger.info("RESPONSE BODY"+respBody);
		return respBody;
	}
	
	@RequestMapping(value="provider/crm/sugar",method=RequestMethod.POST)
	public @ResponseBody String createSugarUser(@RequestBody String session){
		
		SugarCreateUser scu=new SugarCreateUser();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json=gson.toJson(scu);
		
		
		logger.info("JSON DATA CREATE SUGAR"+json);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://localhost/sugarcrm/service/v4/rest.php");
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("method", "set_entry"));
		nvps.add(new BasicNameValuePair("input_type", "JSON"));
		nvps.add(new BasicNameValuePair("response_type", "JSON"));
		nvps.add(new BasicNameValuePair("rest_data", json));
		
	    try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
	   
	    String respBody = null;
	    try{
	         
	        HttpResponse response= httpclient.execute(httppost);
	        logger.info("RESPONSE"+response);
	         respBody = EntityUtils.toString(response.getEntity());
	         logger.info("RESPONSE SUGAR CREATE BODY"+respBody);
	         
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }
	    logger.info("RESPONSE BODY"+respBody);
		return respBody;
	}
	
	@RequestMapping(value="provider/crm/sugar/list",method=RequestMethod.GET)
	public @ResponseBody String listSugarUser(){
		
		SugaruserList scu=new SugaruserList();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json=gson.toJson(scu);
		
		
		logger.info("JSON DATA List SUGAR"+json);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		//HttpGet httpget = new HttpGet("http://localhost/sugarcrm/service/v4/rest.php");
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("method", "get_entry_list"));
		nvps.add(new BasicNameValuePair("input_type", "JSON"));
		nvps.add(new BasicNameValuePair("response_type", "JSON"));
		nvps.add(new BasicNameValuePair("rest_data", json));
		logger.info("BEFORE EXECUTION IN LIST");
	   
		URI urli = null;
		try {
			urli = URIUtils.createURI("http", "localhost", -1,
					"sugarcrm/service/v4/rest.php",
					URLEncodedUtils.format(nvps, "UTF-8"), null);
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HttpGet httpget = new HttpGet(urli);
	   
	    String respBody = null;
	    try{
	         
	        HttpResponse response= httpclient.execute(httpget);
	        logger.info("RESPONSE"+response);
	         respBody = EntityUtils.toString(response.getEntity());
	         logger.info("RESPONSE SUGAR List BODY"+respBody);
	         
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }
	    logger.info("RESPONSE BODY LIST END"+respBody);
		return respBody;
	}
	
	@RequestMapping(value="provider/crm/sugar/accounts",method=RequestMethod.POST)
	public @ResponseBody String createSugarAccounts(@RequestBody String session){
		
		SugarAccountCreate scu=new SugarAccountCreate();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json=gson.toJson(scu);
		
		
		logger.info("JSON DATA CREATE SUGAR"+json);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://localhost/sugarcrm/service/v4/rest.php");
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("method", "set_entry"));
		nvps.add(new BasicNameValuePair("input_type", "JSON"));
		nvps.add(new BasicNameValuePair("response_type", "JSON"));
		nvps.add(new BasicNameValuePair("rest_data", json));
		
	    try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
	   
	    String respBody = null;
	    try{
	         
	        HttpResponse response= httpclient.execute(httppost);
	        logger.info("RESPONSE"+response);
	         respBody = EntityUtils.toString(response.getEntity());
	         logger.info("RESPONSE SUGAR CREATE BODY"+respBody);
	         
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }
	    logger.info("RESPONSE BODY"+respBody);
		return respBody;
	}
	
	@RequestMapping(value="provider/crm/sugar/accounts",method=RequestMethod.GET)
	public @ResponseBody String listSugarAccounts(){
		
		SugaruserList scu=new SugaruserList();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json=gson.toJson(scu);
		
		
		logger.info("JSON DATA List SUGAR"+json);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		//HttpGet httpget = new HttpGet("http://localhost/sugarcrm/service/v4/rest.php");
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("method", "get_entry_list"));
		nvps.add(new BasicNameValuePair("input_type", "JSON"));
		nvps.add(new BasicNameValuePair("response_type", "JSON"));
		nvps.add(new BasicNameValuePair("rest_data", json));
		logger.info("BEFORE EXECUTION IN LIST");
	   
		URI urli = null;
		try {
			urli = URIUtils.createURI("http", "localhost", -1,
					"sugarcrm/service/v4/rest.php",
					URLEncodedUtils.format(nvps, "UTF-8"), null);
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HttpGet httpget = new HttpGet(urli);
	   
	    String respBody = null;
	    try{
	         
	        HttpResponse response= httpclient.execute(httpget);
	        logger.info("RESPONSE"+response);
	         respBody = EntityUtils.toString(response.getEntity());
	         logger.info("RESPONSE SUGAR List BODY"+respBody);
	         
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }
	    logger.info("RESPONSE BODY LIST END"+respBody);
		return respBody;
	}
	
	@RequestMapping(value="provider/crm/sugar/relation",method=RequestMethod.GET)
	public @ResponseBody String listSugarRelation(){
		
		Sugarrelation scu=new Sugarrelation();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json=gson.toJson(scu);
		
		
		logger.info("JSON DATA List SUGAR"+json);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		//HttpGet httpget = new HttpGet("http://localhost/sugarcrm/service/v4/rest.php");
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("method", "get_entry_list"));
		nvps.add(new BasicNameValuePair("input_type", "JSON"));
		nvps.add(new BasicNameValuePair("response_type", "JSON"));
		nvps.add(new BasicNameValuePair("rest_data", json));
		logger.info("BEFORE EXECUTION IN LIST");
	   
		URI urli = null;
		try {
			urli = URIUtils.createURI("http", "localhost", -1,
					"sugarcrm/service/v4/rest.php",
					URLEncodedUtils.format(nvps, "UTF-8"), null);
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HttpGet httpget = new HttpGet(urli);
	   
	    String respBody = null;
	    try{
	         
	        HttpResponse response= httpclient.execute(httpget);
	        logger.info("RESPONSE"+response);
	         respBody = EntityUtils.toString(response.getEntity());
	         logger.info("RESPONSE SUGAR List BODY"+respBody);
	         
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }
	    logger.info("RESPONSE BODY LIST END"+respBody);
		return respBody;
	}
	
	@RequestMapping(value="provider/crm/sugar/accounts",method=RequestMethod.PUT)
	public @ResponseBody String updateSugarAccounts(@RequestBody String session){
		
		SugarAccountUpdate scu=new SugarAccountUpdate();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json=gson.toJson(scu);
		
		
		logger.info("JSON DATA UPDATE SUGAR"+json);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://localhost/sugarcrm/service/v4/rest.php");
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("method", "set_entry"));
		nvps.add(new BasicNameValuePair("input_type", "JSON"));
		nvps.add(new BasicNameValuePair("response_type", "JSON"));
		nvps.add(new BasicNameValuePair("rest_data", json));
		
	    try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
	   
	    String respBody = null;
	    try{
	         
	        HttpResponse response= httpclient.execute(httppost);
	        logger.info("RESPONSE"+response);
	         respBody = EntityUtils.toString(response.getEntity());
	         logger.info("RESPONSE SUGAR UPDATE BODY"+respBody);
	         
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }
	    logger.info("RESPONSE BODY"+respBody);
		return respBody;
	}
	
	@RequestMapping(value="provider/crm/sugar/leads",method=RequestMethod.POST)
	public @ResponseBody String CreateSugarLeads(@RequestBody String session){
		
		SugarLeadCreate scu=new SugarLeadCreate();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json=gson.toJson(scu);
		
		
		logger.info("JSON DATA Create SUGAR LEAD"+json);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://localhost/sugarcrm/service/v4/rest.php");
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("method", "set_entry"));
		nvps.add(new BasicNameValuePair("input_type", "JSON"));
		nvps.add(new BasicNameValuePair("response_type", "JSON"));
		nvps.add(new BasicNameValuePair("rest_data", json));
		
	    try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
	   
	    String respBody = null;
	    try{
	         
	        HttpResponse response= httpclient.execute(httppost);
	        logger.info("RESPONSE"+response);
	         respBody = EntityUtils.toString(response.getEntity());
	         logger.info("RESPONSE SUGAR Create Lead BODY"+respBody);
	         
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }
	    logger.info("RESPONSE BODY"+respBody);
		return respBody;
	}
	
	@RequestMapping(value="provider/crm/sugar/leads",method=RequestMethod.GET)
	public @ResponseBody String listSugarLeads(){
		
		SugaruserList scu=new SugaruserList();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json=gson.toJson(scu);
		
		
		logger.info("JSON DATA List Lead SUGAR"+json);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		//HttpGet httpget = new HttpGet("http://localhost/sugarcrm/service/v4/rest.php");
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("method", "get_entry_list"));
		nvps.add(new BasicNameValuePair("input_type", "JSON"));
		nvps.add(new BasicNameValuePair("response_type", "JSON"));
		nvps.add(new BasicNameValuePair("rest_data", json));
		logger.info("BEFORE EXECUTION IN LIST");
	   
		URI urli = null;
		try {
			urli = URIUtils.createURI("http", "localhost", -1,
					"sugarcrm/service/v4/rest.php",
					URLEncodedUtils.format(nvps, "UTF-8"), null);
		} catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HttpGet httpget = new HttpGet(urli);
	   
	    String respBody = null;
	    try{
	         
	        HttpResponse response= httpclient.execute(httpget);
	        logger.info("RESPONSE"+response);
	         respBody = EntityUtils.toString(response.getEntity());
	         logger.info("RESPONSE SUGAR List Lead BODY"+respBody);
	         
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }
	    logger.info("RESPONSE BODY LIST Lead END"+respBody);
		return respBody;
	}
	
	@RequestMapping(value="provider/crm/sugar/leads",method=RequestMethod.PUT)
	public @ResponseBody String updateSugarLeads(@RequestBody String session){
		
		SugarleadUpdate scu=new SugarleadUpdate();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json=gson.toJson(scu);
		
		
		logger.info("JSON DATA Leads UPDATE SUGAR"+json);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://localhost/sugarcrm/service/v4/rest.php");
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("method", "set_entry"));
		nvps.add(new BasicNameValuePair("input_type", "JSON"));
		nvps.add(new BasicNameValuePair("response_type", "JSON"));
		nvps.add(new BasicNameValuePair("rest_data", json));
		
	    try {
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
	   
	    String respBody = null;
	    try{
	         
	        HttpResponse response= httpclient.execute(httppost);
	        logger.info("RESPONSE"+response);
	         respBody = EntityUtils.toString(response.getEntity());
	         logger.info("RESPONSE SUGAR Leads UPDATE BODY"+respBody);
	         
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }
	    logger.info("RESPONSE Leads BODY End"+respBody);
		return respBody;
	}
}
