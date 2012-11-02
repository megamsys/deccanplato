package org.megam.deccanplato.provider.crm.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.CloudMediatorException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultCloudProviderMediator;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.core.RequestDataBuilder;
import org.megam.deccanplato.provider.salesforce.SalesforceAdapterAccess;
import org.megam.deccanplato.provider.zoho.crm.ZohoAdapterAccess;
import org.megam.deccanplato.provider.zoho.crm.info.Accounts;
import org.megam.deccanplato.provider.zoho.crm.info.Leads;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class ZoHoAdapterTest {
	
	private static RequestData reqData;
	
	@BeforeClass
	public static void setUp() throws IOException {
		System.out.println("setUp ZOHO");
		System.out.println("setUp");
		BufferedReader br = null;
		String inputJsonPath = new File(".").getCanonicalPath()
				+ java.io.File.separator + "src" + java.io.File.separator
				+ "test" + java.io.File.separator + "java"
				+ java.io.File.separator;

		br = new BufferedReader(new FileReader(inputJsonPath + "zoho.json"));

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
		ProviderRegistry registry = (ProviderRegistry) ctx
				.getBean("registry");
		System.out.println("Provider Registry" + registry.toString());
	}
	 @Test
	 public void testZohoAdapterAccess() throws AdapterAccessException{
			System.out.println("ZOHOADAPTERACCESS");
			ZohoAdapterAccess zaa=new ZohoAdapterAccess();
			DataMap dmap=zaa.authenticate(reqData.getGeneral());
			System.out.println(dmap.map().get("OAuth_token"));
			//System.out.println(dmap.map().get("access_token"));
			//System.out.println("DMAP"+dmap.toString());
		}
	 @Test
	   public void testCreateUser() throws CloudMediatorException{
	       DefaultCloudProviderMediator dcm=new DefaultCloudProviderMediator(reqData);
	       System.out.println("Final Result"+dcm.handleRequest());
	   }
	/*@Ignore
	@BeforeClass
	public static void setUp(){
		System.out.println("setUp ZOHO");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho");
		zohotoken =(resource.accept("application/json").get(String.class));
		System.out.println("Access =>" + zohotoken);
	}*/
	
	/*@Test
	public void ZoHogetUser(){
		System.out.println("setUp ZOHOGET");		
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho");
		String response =resource.contentType("application/json").accept("application/json").post(String.class, zohotoken);
		System.out.println("Access =>" + response);
	}*/
	
	/* @Test
	   public void testLeadsXMLGeneration(){
		   Leads leads = new Leads();
		   String input = "";
			//leads.setOwnerId("660777000000053001");
			leads.setAnualRevenue("1000");
			leads.setCity("MEXICO");
			leads.setCompany("MAX");
			leads.setCountry("DETH VALLY");
			leads.setDescription("account dscription");
			leads.setDesignation("Developer");
			leads.setEmail("raja.pandiya@google.co.in");
			leads.setEmailOptOut("String");
			leads.setFax("ofx4586465859699");
			leads.setFirstname("Ricky");
			leads.setIndustry("IT");
			leads.setLastname("Ponting");
			//leads.setLeadOwner("raja.pandiya");
			leads.setLeadSource("Source");
			leads.setLeadStatus("ACTIVE");
			leads.setMobile("95895746325");
			leads.setNoOfEmployees("10");
			leads.setPhone("012-2548963");
			leads.setSalutation("Salutation");
			leads.setSkypeId("4256gtrm");
			leads.setStreet("Mount vally street");
			leads.setWebsite("megam.co");
			leads.setZipCode("4589632");
			try {
				  input=leads.toXMLString();
				  System.out.println(leads.toXMLString());
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("setUp ZOHOLEAD");		
			RestClient rc = new RestClient();
			Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho/leads");
			String response =resource.contentType("text/plain").accept("text/plain").post(String.class, input);
			System.out.println("Access =>" + response);
			
	   }
	@Ignore
	@Test
	public void Usertestdelete(){
		System.out.println("testDeleteAccount :");
		RestClient rc = new RestClient();

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm/zoho");
		String response = resource.contentType("application/json")
				.accept("application/json").delete(String.class);
		System.out.println("testCreateAccount :" + response);
	}
	@Ignore
  
	/*@Test
	public void ZohoTestListLead(){
		System.out.println("setUp ZOHO LEAD List");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho/list");
		zohotoken =(resource.accept("application/json").get(String.class));
		System.out.println("Access =>" + zohotoken);
	}*/
	/*@Test
	public void ZohoTestListAccount(){
		System.out.println("setUp ZOHO Account List");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho/accounts");
		zohotoken =(resource.accept("application/json").get(String.class));
		System.out.println("Access =>" + zohotoken);
	}
	/*@Test
	public void ZohoTestDeleteLead(){
		System.out.println("setUp ZOHO Leads Delete");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho/leads");
		zohotoken =(resource.accept("application/json").delete(String.class));
		System.out.println("Access =>" + zohotoken);
	}
	@Test
	public void ZohoTestDeleteAccounts(){
		System.out.println("setUp ZOHO Leads Delete");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho/account");
		zohotoken =(resource.accept("application/json").delete(String.class));
		System.out.println("Access =>" + zohotoken);
	}*/
	/*@Ignore
	@Test
	   public void testAccountsXMLGeneration(){
		   Accounts zacc=new Accounts();
		   String input = "";
			 zacc.setAccount_Name("Billing");
			 zacc.setAnual_Revenue("1000000");
			 zacc.setEmployees("20");
			 zacc.setFax("424586415");
			 zacc.setPhone("9585791705");
			 //zacc.setSIC_Code("SID4586");
			 zacc.setAccount_owner("raja.pandiya");
			 //zacc.setSmowner("660777000000053001");
		   
			try {
				  input=zacc.toXMLString();
				  System.out.println(zacc.toXMLString());
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("setUp ZOHOLEAD");		
			RestClient rc = new RestClient();
			Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho/accounts");
			String response =resource.contentType("text/plain").accept("text/plain").post(String.class, input);
			System.out.println("Access =>" + response);
			
	   }*/
}
