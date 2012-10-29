package org.megam.deccanplato.provider.crm.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.core.RequestDataBuilder;
import org.megam.deccanplato.provider.sugarcrm.SugarcrmAdapterAccess;
import org.megam.deccanplato.provider.zoho.crm.ZohoAdapterAccess;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

public class SugarAdapterTest {
	
	private static String SUGAR;
	public static String  session;
	public static RequestData reqData;
	
	@BeforeClass
	public static void setUp() throws IOException {
		System.out.println("setUp ZOHO");
		System.out.println("setUp");
		BufferedReader br = null;
		String inputJsonPath = new File(".").getCanonicalPath()
				+ java.io.File.separator + "src" + java.io.File.separator
				+ "test" + java.io.File.separator + "java"
				+ java.io.File.separator;

		br = new BufferedReader(new FileReader(inputJsonPath + "sugarcrm.json"));

		StringBuilder strb = new StringBuilder();
		String currentLine = "";

		while ((currentLine = br.readLine()) != null) {
			strb.append(currentLine);
		}
		//
		RequestDataBuilder rdb = new RequestDataBuilder(strb.toString());
		reqData=rdb.data();
		System.out.println(rdb.data().toString());	    	
		
	}
	 @Test
		public void testZohoAdapterAccess() throws AdapterAccessException{
			
			SugarcrmAdapterAccess zaa=new SugarcrmAdapterAccess();
			DataMap dmap=zaa.authenticate(reqData.getGeneral());
			System.out.println(dmap.map().get("id"));
			//System.out.println(dmap.map().get("access_token"));
			//System.out.println("DMAP"+dmap.toString());
		}
	
	/*@BeforeClass
	public static void setUp(){
		System.out.println("setUp SUGAR");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar");
		zohotoken =(resource.accept("application/json").get(String.class));
		JSONObject Json;
		try {
			Json=new JSONObject(zohotoken);
			session=Json.getString("id");
			System.out.println("SESSION ID:::::"+session);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Access =>" + zohotoken);
	}*/
	/*@Test
	public void testcreateuser(){
		System.out.println("setUp SUGAR Create");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar");
		zohotoken =(resource.accept("application/json").post(String.class,session));
		System.out.println("Access =>" + zohotoken);
	}
	@Test
	public void testlistuser(){
		System.out.println("setUp SUGAR List");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar/list");
		String Listuser =(resource.accept("application/json").get(String.class));
		System.out.println("Access =>" + Listuser);
	}
	@Test
	public void testcreateAccounts(){
		System.out.println("setUp SUGAR Create");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar/accounts");
		zohotoken =(resource.accept("application/json").post(String.class,session));
		System.out.println("Access =>" + zohotoken);
	}*/
	/*@Ignore
	@Test
	public void testlistAccounts(){
		System.out.println("setUp SUGAR List");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar/accounts");
		String Listuser =(resource.accept("application/json").get(String.class));
		System.out.println("Access =>" + Listuser);
	}
	//To Do:Not working
	@Ignore
	@Test
	public void testlistRelation(){
		System.out.println("setUp SUGAR account List");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar/relation");
		String Listuser =(resource.accept("application/json").get(String.class));
		System.out.println("Access =>" + Listuser);
	}
	@Ignore
	@Test
	public void testupdateAccounts(){
		System.out.println("setUp SUGAR UPdate");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar/accounts");
		zohotoken =(resource.accept("application/json").put(String.class,session));
		System.out.println("Access =>" + zohotoken);
	}
	@Ignore
	@Test
	public void testCreateLeadss(){
		System.out.println("setUp SUGAR Create Leads");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar/leads");
		zohotoken =(resource.accept("application/json").post(String.class,session));
		System.out.println("Access =>" + zohotoken);
	}
	@Test
	public void testlistLeads(){
		System.out.println("setUp SUGAR Lead List");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar/leads");
		String Listuser =(resource.accept("application/json").get(String.class));
		System.out.println("Access =>" + Listuser);
	}
	@Ignore
	@Test
	public void testupdateLeads(){
		System.out.println("setUp SUGAR Leads UPdate");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar/leads");
		zohotoken =(resource.accept("application/json").put(String.class,session));
		System.out.println("Access =>" + zohotoken);
	}*/
}
