/**
 * “Copyright 2012 Megam Systems”
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.megam.deccanplato.provider.crm.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.CloudMediatorException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultCloudProviderMediator;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.core.RequestDataBuilder;
import org.megam.deccanplato.provider.crm.test.common.CommonTest;
import org.megam.deccanplato.provider.zoho.invoice.ZohoInvoiceAdapterAccess;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author pandiyaraja
 *
 */
public class ZohoInvoiceAdapterTest {

	private static final String ZOHOINVOICE="zohoinvoice";
	
    @Test
	public  void zohoInvoice(){	
    	List<String> functionList=new ArrayList<String>();
    	functionList.add("expensecategory");
    	functionList.add("expense");
    	functionList.add("item");
    	functionList.add("payment");
    	
    	List<String> customerList = new ArrayList<String>();
    	customerList.add("create");
    	customerList.add("list");
    	customerList.add("update");
    	customerList.add("delete");
    	customerList.add("view");
    	
    	List<String> invoiceList = new ArrayList<String>();
    	invoiceList.add("create");
    	invoiceList.add("list");
    	invoiceList.add("update");
    	invoiceList.add("delete");
    	invoiceList.add("convert");
    	invoiceList.add("pdf");
    	invoiceList.add("remind");
    	invoiceList.add("send");
    	
    	List<String> estimateList=new ArrayList<String>();
    	estimateList.add("create");
    	estimateList.add("list");
    	estimateList.add("update");
    	estimateList.add("delete");
    	estimateList.add("pdf");
    	estimateList.add("send");
    	estimateList.add("mark");
    	
    	List<String> busiActivity = new ArrayList<String>();
		busiActivity.add("create");
		busiActivity.add("list");
		busiActivity.add("update");
		busiActivity.add("delete");
		GenericApplicationContext ctx = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		xmlReader.loadBeanDefinitions(new ClassPathResource(
				"applicationContext.xml"));
		ctx.refresh();
		ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");
		
		for(String function: functionList) {
			for(String activity: busiActivity) {
				CommonTest ctest=new CommonTest();
				RequestData reqData;
				reqData=ctest.commonTest(function, activity, ZOHOINVOICE);
				if(function.equals("user") && activity.equals("create")) {
				testAdapterAccess(reqData);
				}
				ctest.testBusinessImpl();
			}
		}
		for(int i=0;i<1;i++) {
			String function="customer";
			for(String activity: customerList) {
				CommonTest ctest=new CommonTest();
				RequestData reqData;
				reqData=ctest.commonTest(function, activity, ZOHOINVOICE);
				if(function.equals("custoer") && activity.equals("create")) {
				testAdapterAccess(reqData);
				}
				ctest.testBusinessImpl();
			}
		}
		for(int i=0;i<1;i++) {
			String function="invoice";
			for(String activity: invoiceList) {
				CommonTest ctest=new CommonTest();
				RequestData reqData;
				reqData=ctest.commonTest(function, activity, ZOHOINVOICE);
				if(function.equals("invoice") && activity.equals("create")) {
				testAdapterAccess(reqData);
				}
				ctest.testBusinessImpl();
			}
		}
		for(int i=0;i<1;i++) {
			String function="estimate";
			for(String activity: estimateList) {
				CommonTest ctest=new CommonTest();
				RequestData reqData;
				reqData=ctest.commonTest(function, activity, ZOHOINVOICE);
				if(function.equals("estimate") && activity.equals("create")) {
				testAdapterAccess(reqData);
				}
				ctest.testBusinessImpl();
			}
		}

	}
   	private void testAdapterAccess(RequestData reqData) {
			
			ZohoInvoiceAdapterAccess zaa=new ZohoInvoiceAdapterAccess();
			try {
				DataMap dmap=zaa.authenticate(reqData.getGeneral());
			} catch (AdapterAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
   
}
