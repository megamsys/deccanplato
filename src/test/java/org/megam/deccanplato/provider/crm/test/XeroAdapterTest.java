/* 
** Copyright [2012] [Megam Systems]
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
** http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/
package org.megam.deccanplato.provider.crm.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.crm.test.common.CommonTest;
import org.megam.deccanplato.provider.xero.XeroAdapterAccess;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author pandiyaraja
 *
 */
public class XeroAdapterTest {
	
	private static final String XERO="xero";
    @Test 
	public void xeroTest() {
		GenericApplicationContext ctx = new GenericApplicationContext();
    	XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
    	xmlReader.loadBeanDefinitions(new ClassPathResource(
    			"applicationContext.xml"));
    	ctx.refresh();
    	ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");
    	
    	List<String> oauth= new ArrayList<String>();
    	//oauth.add("invoice");
    	oauth.add("account");
    	List<String> oauthList=new ArrayList<String>();
    	//oauthList.add("list");
    	oauthList.add("view");
    	//oauthList.add("create");
    	//oauthList.add("update");
    	for(String activity: oauth) {
    	    for(String function: oauthList) {
			
				CommonTest ctest=new CommonTest();
				RequestData reqData;
				reqData=ctest.commonTest(activity, function, XERO);
				if(function.equalsIgnoreCase("list") && activity.equalsIgnoreCase("invoice")) {
				testAdapterAccess(reqData);
				}
				ctest.testBusinessImpl();		
			
		    }
    	}
	}
	private void testAdapterAccess(RequestData reqData)  {

		XeroAdapterAccess saa = new XeroAdapterAccess();
		try {
			DataMap dmap = saa.authenticate(reqData.getGeneral());
		} catch (AdapterAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
