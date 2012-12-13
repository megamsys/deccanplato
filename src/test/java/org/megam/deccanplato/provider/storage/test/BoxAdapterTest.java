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
package org.megam.deccanplato.provider.storage.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.box.BoxAdapterAccess;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.core.RequestDataBuilder;
import org.megam.deccanplato.provider.salesforce.crm.SalesforceCRMAdapterAccess;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author alrin
 *
 */
public class BoxAdapterTest {
	private static RequestData reqData;
	private RequestDataBuilder rdb;
	
	@BeforeClass
	public static void setUp() throws IOException {
		
		BufferedReader br = null;
		String inputJsonPath = new File(".").getCanonicalPath()
				+ java.io.File.separator + "src" + java.io.File.separator
				+ "test" + java.io.File.separator + "java"
				+ java.io.File.separator;

		br = new BufferedReader(new FileReader(inputJsonPath + "box.json"));

		StringBuilder strb = new StringBuilder();
		String currentLine = "";

		while ((currentLine = br.readLine()) != null) {
			strb.append(currentLine);
		}
		RequestDataBuilder rdb = new RequestDataBuilder(strb.toString());
		reqData=rdb.data();
		
	    
		GenericApplicationContext ctx = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		xmlReader.loadBeanDefinitions(new ClassPathResource(
				"applicationContext.xml"));
		ctx.refresh();		
		ProviderRegistry registry = (ProviderRegistry) ctx
				.getBean("registry");
			
	}
	 @Test
		public void testBoxAdapterAccess() throws AdapterAccessException{
			
			BoxAdapterAccess baa=new BoxAdapterAccess();
			DataMap dmap=baa.authenticate(reqData.getOutput());
			
		}

}
