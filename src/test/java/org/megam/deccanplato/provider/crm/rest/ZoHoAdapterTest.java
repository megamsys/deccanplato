package org.megam.deccanplato.provider.crm.rest;

import java.io.IOException;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ZoHoAdapterTest {
	
	private static String zohotoken;
	/*@Ignore
	@BeforeClass
	public static void setUp(){
		System.out.println("setUp ZOHO");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho");
		zohotoken =(resource.accept("application/json").get(String.class));
		System.out.println("Access =>" + zohotoken);
	}
	@Ignore
	@Test
	public void ZoHogetUser(){
		System.out.println("setUp ZOHOGET");		
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho");
		String response =resource.contentType("application/json").accept("application/json").post(String.class, zohotoken);
		System.out.println("Access =>" + response);
	}
	@Ignore
	@Test
	public void Accounttestdelete(){
		System.out.println("testDeleteAccount :");
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/zoho");
		String response = resource.contentType("application/json")
				.accept("application/json").delete(String.class);
		System.out.println("testCreateAccount :" + response);
	}*/
   @Test
   public void testXMLGeneration(){
	  
   }
}
