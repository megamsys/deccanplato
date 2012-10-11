package org.megam.deccanplato.provider.crm.rest;

import static org.junit.Assert.*;

import org.apache.wink.client.RestClient;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.megam.deccanplato.provider.crm.info.SalesforceCRM;

import org.apache.wink.client.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SaleforceAdapterTest {

	private static String access_stuff;

	@BeforeClass
	public static void setUp() {
		System.out.println("setUp");
		RestClient rc = new RestClient();
		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm");
		access_stuff = resource.accept("application/json").get(String.class);
		System.out.println("Access =>" + access_stuff);
	}

	
	@Test
	public void testCreateUser() {
		System.out.println("testCreateUser :" + access_stuff);
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, access_stuff);
		System.out.println("testCreateUser :" + response);

	}

	@Test
	public void testListUser() {
		System.out.println("testListUser :"+ access_stuff);
		RestClient rc = new RestClient();
		Gson gson = new GsonBuilder().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);
		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/list");
		String response = resource.accept("application/json").get(String.class);
		System.out.println("testListUser :"+response);

	}
	@Test
	public void ZoHoOAuth(){
		System.out.println("setUp ZOHO");
		RestClient rc = new RestClient();
		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/ZoHo");
		String response = resource.accept("application/json").get(String.class);
		System.out.println("Access =>" + response);
	}
}
