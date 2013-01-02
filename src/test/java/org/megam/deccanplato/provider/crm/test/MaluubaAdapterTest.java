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
import org.megam.deccanplato.provider.maluuba.MaluubaAdapterAccess;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author pandiyaraja
 *
 */
public class MaluubaAdapterTest {
	private static final String MALUUBA="maluuba";
	@Test
	public void maluubaTest() {
		GenericApplicationContext ctx = new GenericApplicationContext();
    	XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
    	xmlReader.loadBeanDefinitions(new ClassPathResource(
    			"applicationContext.xml"));
    	ctx.refresh();
    	ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");
    	List<String> busiMethod =new ArrayList<String>();
		busiMethod.add("normalize");
		//busiMethod.add("interpret");
		List<String> busiActivity = new ArrayList<String>();
		busiActivity.add("norm");
		
		for(String function: busiMethod) {
			for(String activity: busiActivity) {
				CommonTest ctest=new CommonTest();
				RequestData reqData;
				reqData=ctest.commonTest(function, activity, MALUUBA);
				
				testAdapterAccess(reqData);
				
				ctest.testBusinessImpl();
			}
		}	
	}
	private void testAdapterAccess(RequestData reqData)  {

		MaluubaAdapterAccess maa = new MaluubaAdapterAccess();
		try {
			DataMap dmap = maa.authenticate(reqData.getGeneral());
		} catch (AdapterAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}   

}
