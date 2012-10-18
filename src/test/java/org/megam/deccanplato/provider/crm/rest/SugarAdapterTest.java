package org.megam.deccanplato.provider.crm.rest;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

public class SugarAdapterTest {
	
	private static String zohotoken;
	public static String  session;
	
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
	@Test
	public void testcreateuser(){
		System.out.println("setUp SUGAR");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/sugar");
		zohotoken =(resource.accept("application/json").post(String.class,session));
		System.out.println("Access =>" + zohotoken);
	}
}
