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
package org.megam.deccanplato.provider.storage.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.box.BoxAdapterAccess;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.crm.test.common.CommonTest;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author pandiyaraja
 *
 */
public class BoxAdapterTest {

	private static final String BOX="box";
    @Test
	public void boxAdapterTest() {
	GenericApplicationContext ctx = new GenericApplicationContext();
	XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
	xmlReader.loadBeanDefinitions(new ClassPathResource(
			"applicationContext.xml"));
	ctx.refresh();
	ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");
	
	final String file="file";
	final String folder="folder";
	final String user="user";
	
	List<String> busiActivity = new ArrayList<String>();
	//busiActivity.add("upload");
	//busiActivity.add("download");
	busiActivity.add("delete");
	//busiActivity.add("share");
	//busiActivity.add("view");
	
	List<String> folderList=new ArrayList<>();
	folderList.add("retrive");
	
	List<String> userList=new ArrayList<>();
	//userList.add("create");
	userList.add("list");
	//userList.add("delete");
	
	for(String activity: busiActivity) {
			CommonTest ctest=new CommonTest();
			RequestData reqData;
			reqData=ctest.commonTest(file, activity, BOX);
			if(file.equalsIgnoreCase("file")&activity.equalsIgnoreCase("upload")) {
			testAdapterAccess(reqData);
			System.out.println("REQDATA:"+reqData);
			}
			ctest.testBusinessImpl();
		}/*
	
	for(String activity: folderList) {
		  CommonTest ctest=new CommonTest();
		  RequestData reqData;
		  reqData=ctest.commonTest(folder, activity, BOX);
		  if(folder.equalsIgnoreCase("folder")&activity.equalsIgnoreCase("retrive")) {
			  testAdapterAccess(reqData);			  
		  }
		  ctest.testBusinessImpl();
		}*/
	/*for(String activity: userList) {
		  CommonTest ctest=new CommonTest();
		  RequestData reqData;
		  reqData=ctest.commonTest(user, activity, BOX);
		  if(folder.equalsIgnoreCase("user")&activity.equalsIgnoreCase("create")) {
			  testAdapterAccess(reqData);			  
		  }
		  ctest.testBusinessImpl();
		}*/
    }
    public void testAdapterAccess(RequestData reqData)  {

    	BoxAdapterAccess saa = new BoxAdapterAccess();
    	try {
    		DataMap dmap = saa.authenticate(reqData.getGeneral());
    	} catch (AdapterAccessException e) {
    		e.printStackTrace();
    	}
    }   
}
