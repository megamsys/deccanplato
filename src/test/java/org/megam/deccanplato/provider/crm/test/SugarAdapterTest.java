package org.megam.deccanplato.provider.crm.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.megam.deccanplato.provider.crm.test.common.CommonTest;
import org.megam.deccanplato.provider.salesforce.crm.SalesforceCRMAdapterAccess;
import org.megam.deccanplato.provider.sugarcrm.SugarCRMAdapterAccess;
import org.megam.deccanplato.provider.zoho.crm.ZohoCRMAdapterAccess;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

public class SugarAdapterTest {
	private static final String SUGARCRM="sugarcrm";
    @Test
	public void sugaradapterTest() {
	GenericApplicationContext ctx = new GenericApplicationContext();
	XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
	xmlReader.loadBeanDefinitions(new ClassPathResource(
			"applicationContext.xml"));
	ctx.refresh();
	ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");
	
	

	List<String> busiMethod =new ArrayList<String>();
	//busiMethod.add("user");
	//busiMethod.add("account");
	//busiMethod.add("lead");
	//busiMethod.add("contact");
	//busiMethod.add("opportunitie");
	//busiMethod.add("campaign");
	//busiMethod.add("task");
	//busiMethod.add("case");
	busiMethod.add("call");
	//busiMethod.add("meeting");
	//busiMethod.add("note");
	List<String> busiActivity = new ArrayList<String>();
	//busiActivity.add("create");
	//busiActivity.add("list");
	busiActivity.add("update");
	//busiActivity.add("delete");	
	for(String function: busiMethod) {
		for(String activity: busiActivity) {
			CommonTest ctest=new CommonTest();
			RequestData reqData;
			reqData=ctest.commonTest(function, activity, SUGARCRM);
			if(function.equalsIgnoreCase("user")&activity.equalsIgnoreCase("create")) {
			testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
	}
						
}
public void testAdapterAccess(RequestData reqData)  {

	SugarCRMAdapterAccess saa = new SugarCRMAdapterAccess();
	try {
		DataMap dmap = saa.authenticate(reqData.getGeneral());
	} catch (AdapterAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}   
}
