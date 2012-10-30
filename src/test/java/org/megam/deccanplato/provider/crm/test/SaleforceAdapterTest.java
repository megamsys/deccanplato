package org.megam.deccanplato.provider.crm.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.wink.client.RestClient;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.megam.deccanplato.core.test.CoreTest;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.core.AccessInfo;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.CloudMediatorException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultCloudProviderMediator;
import org.megam.deccanplato.provider.core.ProviderInfo;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.core.RequestDataBuilder;
import org.megam.deccanplato.provider.salesforce.SalesforceAdapterAccess;
import org.megam.deccanplato.provider.salesforce.info.SalesforceCRM;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.apache.wink.client.Resource;
import org.apache.wink.common.internal.model.admin.Registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SaleforceAdapterTest {

	private static RequestData reqData;
	RequestDataBuilder rdb;
	
    
	@BeforeClass
	public static void setUp() throws IOException {
		System.out.println("setUp");
		BufferedReader br = null;
		String inputJsonPath = new File(".").getCanonicalPath()
				+ java.io.File.separator + "src" + java.io.File.separator
				+ "test" + java.io.File.separator + "java"
				+ java.io.File.separator;

		br = new BufferedReader(new FileReader(inputJsonPath + "salesforce.json"));

		StringBuilder strb = new StringBuilder();
		String currentLine = "";

		while ((currentLine = br.readLine()) != null) {
			strb.append(currentLine);
		}
		//
		RequestDataBuilder rdb = new RequestDataBuilder(strb.toString());
		reqData=rdb.data();
		System.out.println(rdb.data().toString());
	    
		GenericApplicationContext ctx = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		xmlReader.loadBeanDefinitions(new ClassPathResource(
				"applicationContext.xml"));
		ctx.refresh();
		ProviderRegistry myBean = (ProviderRegistry) ctx
				.getBean("providerRegistry");
		System.out.println("Provider Registry" + myBean.toString());
	    	
	}
    @Test
	public void testSalesforceAdapterAccess() throws AdapterAccessException{
		
		SalesforceAdapterAccess saa=new SalesforceAdapterAccess();
		DataMap dmap=saa.authenticate(reqData.getGeneral());
		System.out.println(dmap.map().get("instance_url"));
		System.out.println(dmap.map().get("access_token"));
		//System.out.println("DMAP"+dmap.toString());
	}
    @Test
   public void testCreateUser() throws CloudMediatorException{
    	Registry reg=new Registry();
	   DefaultCloudProviderMediator dcm=new DefaultCloudProviderMediator(reqData);
	   System.out.println("Final Result"+dcm.handleRequest());
   }
	
	/*@Ignore
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
    @Ignore   
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
	@Ignore
	@Test
	public void SalesforceAccount(){
		System.out.println("testCreateAccount :" + access_stuff);
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/account");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, access_stuff);
		System.out.println("testCreateAccount :" + response);
	}
	
	@Test
	public void testListAccount() {
		System.out.println("testListAccount :"+ access_stuff);
		RestClient rc = new RestClient();
		Gson gson = new GsonBuilder().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);
		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/account/list");
		String response = resource.accept("application/json").get(String.class);
		System.out.println("testListAccount :"+response);

	}
	@Ignore
	@Test
	public void Accountdelete(){
		System.out.println("testDeleteAccount :" + access_stuff);
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/account");
		String response = resource.contentType("application/json")
				.accept("application/json").delete(String.class);
		System.out.println("testCreateAccount :" + response);
	}*/
	/*@Ignore
	@Test
	public void SalesforceLead(){
		System.out.println("testCreateLead :" + access_stuff);
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/leads");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, access_stuff);
		System.out.println("testCreateAccount :" + response);
	}
	
	@Test
	public void testListLead() {
		System.out.println("testListLead :"+ access_stuff);
		RestClient rc = new RestClient();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SalesforceCRM salesforceCRM = gson.fromJson(access_stuff,
				SalesforceCRM.class);
		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/leads");
		String response = resource.accept("application/json").get(String.class);
		System.out.println("testListLead :"+response);

	}
	@Test
	public void Leaddelete(){
		System.out.println("testDeleteLead :");
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/leads");
		String response = resource.contentType("application/json")
				.accept("application/json").delete(String.class);
		System.out.println("testdeleteLead :" + response);
	}*/
}
