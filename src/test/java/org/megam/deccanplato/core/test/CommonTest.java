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
package org.megam.deccanplato.core.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.CloudMediatorException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultCloudProviderMediator;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.core.RequestDataBuilder;
import org.megam.deccanplato.provider.salesforce.crm.SalesforceCRMAdapterAccess;
import org.megam.deccanplato.provider.sugarcrm.SugarCRMAdapterAccess;
import org.megam.deccanplato.provider.zoho.crm.ZohoCRMAdapterAccess;

import com.google.gson.Gson;

/**
 * @author pandiyaraja
 *
 */
public class CommonTest {
	/*private static RequestData reqData;
	RequestDataBuilder rdb;

	public void testCommonTest(String provider, String activity)
			throws IOException {
		
		BufferedReader br = null;
		String inputJsonPath = new File(".").getCanonicalPath()
				+ java.io.File.separator + "src" + java.io.File.separator
				+ "test" + java.io.File.separator + "resources"
				+ java.io.File.separator + provider + java.io.File.separator
				+ activity + java.io.File.separator;

		br = new BufferedReader(new FileReader(inputJsonPath));

		StringBuilder strb = new StringBuilder();
		String currentLine = "";

		while ((currentLine = br.readLine()) != null) {
			strb.append(currentLine);
		}

		RequestDataBuilder rdb = new RequestDataBuilder(strb.toString());
		reqData = rdb.data();
		
		
		if (activity.equalsIgnoreCase("user_create") && provider.equalsIgnoreCase("salesforce")) {			
				testSalesforceAdapterAccess();
		}		
		if (activity.equalsIgnoreCase("user_create") && provider.equalsIgnoreCase("zoho")) {			
			testZohoAdapterAccess();
	}	
		if (activity.equalsIgnoreCase("user_create") && provider.equalsIgnoreCase("sugarcrm")) {			
			testSugarAdapterAccess();
	}	
		try {
			testCase();
		} catch (CloudMediatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void testSalesforceAdapterAccess() {

		SalesforceCRMAdapterAccess saa = new SalesforceCRMAdapterAccess();
		DataMap dmap;
		try {
			dmap = saa.authenticate(reqData.getGeneral());
		} catch (AdapterAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	private void testZohoAdapterAccess() {
		
		ZohoCRMAdapterAccess zaa=new ZohoCRMAdapterAccess();		
		DataMap dmap;
		try {
			dmap = zaa.authenticate(reqData.getGeneral());
		} catch (AdapterAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	private void testSugarAdapterAccess(){
		
		SugarCRMAdapterAccess zaa=new SugarCRMAdapterAccess();
		DataMap dmap;
		try {
			dmap = zaa.authenticate(reqData.getGeneral());
				
		} catch (AdapterAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}	
	private void testCase() throws CloudMediatorException {
		DefaultCloudProviderMediator dcm = new DefaultCloudProviderMediator(
				reqData);
	}*/
	@Test
	public void test() {
	body B=new body();
	Gson gson=new Gson();
	System.out.println(gson.toJson(B));
	}
}
