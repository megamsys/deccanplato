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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.core.RequestDataBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.megam.deccanplato.provider.crm.test.*;

/**
 * @author pandiyaraja
 * 
 */
public class ProvidersTest {

	private static RequestData reqData;
	CommonTest ct=new CommonTest();
	RequestDataBuilder rdb;

	/*
	 * LinkedList<String> salesforcelink=new LinkedList<>(); LinkedList<String>
	 * zoholink=new LinkedList<>(); LinkedList<String> sugarcrmlink=new
	 * LinkedList<>(); LinkedList<String> googleapplink=new LinkedList<>();
	 */

	@Test
	public void allTest() throws IOException {

		GenericApplicationContext ctx = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		xmlReader.loadBeanDefinitions(new ClassPathResource(
				"applicationContext.xml"));
		ctx.refresh();
		ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");
		

		List<String> providerlist = new ArrayList<String>();
		//providerlist.add("salesforce");
		//providerlist.add("sugarcrm");
		providerlist.add("zohocrm");
		
		List<String> userlist = new ArrayList<String>();
		userlist.add("user_create.json");
		userlist.add("user_list.json");
		userlist.add("user_update.json");
		userlist.add("user_delete.json");

		List<String> accountlist = new ArrayList<String>();
		accountlist.add("account_create.json");
		accountlist.add("account_list.json");
		accountlist.add("account_update.json");
		accountlist.add("account_delete.json");

		List<String> leadlist = new ArrayList<String>();
		leadlist.add("lead_create.json");
		leadlist.add("lead_list.json");
		leadlist.add("lead_update.json");
		leadlist.add("lead_delete.json");

		List<String> contactlist = new ArrayList<String>();
		contactlist.add("contact_create.json");
		contactlist.add("contact_list.json");
		contactlist.add("contact_update.json");
		contactlist.add("contact_delete.json");

		List<String> migratelist = new ArrayList<String>();
		migratelist.add("migration.json");
/*
		for (String provid : providerlist) {
			for (String user : userlist) {
				ct.testCommonTest(provid, user);
			}
			for(String account: accountlist) {
				ct.testCommonTest(provid, account);
			}
			for(String lead: leadlist) {
				ct.testCommonTest(provid, lead);
			}
		}		
			for(String user: userlist) {
				ct.testCommonTest("googleapps", user);
			}
			for(String contact: contactlist) {
				ct.testCommonTest("googleapps", contact);
			}
			for(String migrate: migratelist) {
				ct.testCommonTest("googleapps", migrate);
			}	*/	
	}

}
