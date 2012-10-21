package org.megam.deccanplato.provider.crm.rest;

import javax.xml.bind.JAXBException;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.junit.Ignore;
import org.junit.Test;
import org.megam.deccanplato.provider.crm.info.ZohoCRMAccounts;
import org.megam.deccanplato.provider.crm.info.ZohoCRMLeeds;

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
	
	@Test
	public void ZoHogetUser(){
		System.out.println("setUp ZOHOGET");		
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho");
		String response =resource.contentType("application/json").accept("application/json").post(String.class, zohotoken);
		System.out.println("Access =>" + response);
	}*/
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
   @Test
   public void testLeadsXMLGeneration(){
	   ZohoCRMLeeds leads = new ZohoCRMLeeds();
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
	/*@Test
	public void ZohoTestListLead(){
		System.out.println("setUp ZOHO LEAD List");
		RestClient rc = new RestClient();
		Resource resource = rc.resource("http://localhost:8080/deccanplato/provider/crm/zoho/list");
		zohotoken =(resource.accept("application/json").get(String.class));
		System.out.println("Access =>" + zohotoken);
	}*/
	@Test
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
	@Ignore
	@Test
	   public void testAccountsXMLGeneration(){
		   ZohoCRMAccounts zacc=new ZohoCRMAccounts();
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
			
	   }
}
