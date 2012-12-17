package org.megam.deccanplato.provider.crm.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.crm.test.common.CommonTest;
import org.megam.deccanplato.provider.salesforce.crm.SalesforceCRMAdapterAccess;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class SaleforceCRMAdapterTest {

	private static final String SALESFORCE="salesforcecrm";
    @Test
	public void salesforceTest() { 
    	
    	GenericApplicationContext ctx = new GenericApplicationContext();
    	XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
    	xmlReader.loadBeanDefinitions(new ClassPathResource(
    			"applicationContext.xml"));
    	ctx.refresh();
    	ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");
    	 
		List<String> busiMethod =new ArrayList<String>();
		busiMethod.add("user");
		//busiMethod.add("account");
		//busiMethod.add("lead");
		//busiMethod.add("campaign");
		//busiMethod.add("case");
		//busiMethod.add("contact");
		//busiMethod.add("contract");
		//busiMethod.add("event");
		//busiMethod.add("opportunity");
		//busiMethod.add("solution");
		//busiMethod.add("product");
		//busiMethod.add("task");
		//busiMethod.add("pricebook");
		//busiMethod.add("partner");
		List<String> busiActivity = new ArrayList<String>();
		//busiActivity.add("create");
		busiActivity.add("list");
		//busiActivity.add("update");
		//busiActivity.add("delete");
		for(String function: busiMethod) {
			for(String activity: busiActivity) {
				CommonTest ctest=new CommonTest();
				RequestData reqData;
				reqData=ctest.commonTest(function, activity, SALESFORCE);
				if(function.equalsIgnoreCase("user") && activity.equalsIgnoreCase("create")) {
				testAdapterAccess(reqData);
				}
				ctest.testBusinessImpl();
			}
		}					
	}
    private void testAdapterAccess(RequestData reqData)  {

		SalesforceCRMAdapterAccess saa = new SalesforceCRMAdapterAccess();
		try {
			DataMap dmap = saa.authenticate(reqData.getGeneral());
		} catch (AdapterAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}   
}
