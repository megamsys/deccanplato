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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.crm.test.common.CommonTest;
import org.megam.deccanplato.provider.salesforce.chatter.SalesforceChatterAdapterAccess;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author pandiyaraja
 * 
 */
public class SalesforceChatterAdapterTest {

	private static final String SALESFORCE = "salesforcechatter";

	@Test
	public void salesforceTest() {

		GenericApplicationContext ctx = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		xmlReader.loadBeanDefinitions(new ClassPathResource(
				"applicationContext.xml"));
		ctx.refresh();
		ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");

		final String feed = "feed";
		final String fileFeed="filefeed";
		final String userResources="user";
		final String groupFeed="groupfeed";
		final String influence="influence";
		final String likes="like";
		final String news="news";
		final String people="people";
		final String record="record";
		
		List<String> recordList = new ArrayList<String>();
		recordList.add("list");
		recordList.add("view");
		
		List<String> peopleList = new ArrayList<String>();
		peopleList.add("feed");
		peopleList.add("list");
		
		List<String> newsList = new ArrayList<String>();
		newsList.add("list");
		newsList.add("feed");
		
		List<String> likeList=new ArrayList<String>();
		likeList.add("list");
		likeList.add("view");
		likeList.add("delete");

		List<String> feedList = new ArrayList<String>();
		feedList.add("postcomment");
		//feedList.add("feed");
		//feedList.add("list");
		//feedList.add("comment");
		//feedList.add("like");
		//feedList.add("delete");

		List<String> fileList = new ArrayList<String>();
		fileList.add("list");

		List<String> userList = new ArrayList<String>();
		userList.add("list");
		userList.add("view");
		userList.add("conversation");
		userList.add("conversationview");
		userList.add("file");
		userList.add("message");
		userList.add("messageview");
		userList.add("status");

		List<String> influinceList = new ArrayList<String>();
		influinceList.add("list");

		List<String> groupList = new ArrayList<String>();
		groupList.add("list");
		groupList.add("membership");
		groupList.add("view");	
		groupList.add("file");
		groupList.add("member");
		groupList.add("delete");
		

		for (String activity : feedList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(feed, activity, SALESFORCE);
			if (feed.equalsIgnoreCase("feed")
					&& activity.equalsIgnoreCase("feed")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}

		/*for (String activity : fileList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(fileFeed, activity, SALESFORCE);
			if (fileFeed.equalsIgnoreCase("chatteransweractivity")
					&& activity.equalsIgnoreCase("list")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
		for (String activity : userList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(userResources, activity, SALESFORCE);
			if (userResources.equalsIgnoreCase("user")
					&& activity.equalsIgnoreCase("list")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
		for (String activity : groupList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(groupFeed, activity, SALESFORCE);
			if (groupFeed.equalsIgnoreCase("groupfeed")
					&& activity.equalsIgnoreCase("membership")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
		for (String activity : influinceList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(influence, activity, SALESFORCE);
			if (influence.equalsIgnoreCase("influence")
					&& activity.equalsIgnoreCase("list")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
		for (String activity : likeList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(likes, activity, SALESFORCE);
			if (likes.equalsIgnoreCase("like")
					&& activity.equalsIgnoreCase("list")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
		for (String activity : newsList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(news, activity, SALESFORCE);
			if (news.equalsIgnoreCase("news")
					&& activity.equalsIgnoreCase("list")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
		for (String activity : peopleList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(people, activity, SALESFORCE);
			if (people.equalsIgnoreCase("people")
					&& activity.equalsIgnoreCase("feed")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
		for (String activity : recordList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(record, activity, SALESFORCE);
			if (record.equalsIgnoreCase("record")
					&& activity.equalsIgnoreCase("list")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}*/
			
	}
	

	private void testAdapterAccess(RequestData reqData) {

		SalesforceChatterAdapterAccess saa = new SalesforceChatterAdapterAccess();
		try {
			DataMap dmap = saa.authenticate(reqData.getGeneral());
		} catch (AdapterAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
