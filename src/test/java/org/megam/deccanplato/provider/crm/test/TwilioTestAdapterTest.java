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
import org.junit.experimental.theories.suppliers.TestedOn;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.crm.test.common.CommonTest;
import org.megam.deccanplato.provider.twilio.TwilioAdapterAccess;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author pandiyaraja
 *
 */
public class TwilioTestAdapterTest {

	private static final String TWILIO="twilio";
	
	@Test
	public void twilioAdapterTest() {
		GenericApplicationContext ctx = new GenericApplicationContext();
    	XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
    	xmlReader.loadBeanDefinitions(new ClassPathResource(
    			"applicationContext.xml"));
    	ctx.refresh();
    	ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");
    	
        final String account="account";
        final String availableNumbers="availablenumbers";
        final String callerId="callerid";
        final String incomingNumber="incomingnumber";
        final String sms="sms";
        final String calls="calls";
        final String application="application";
        
        List<String> appsList=new ArrayList<String>();
        //appsList.add("create");
        //appsList.add("list");
        //appsList.add("view");
        //appsList.add("update");
        appsList.add("delete");
        
        List<String> callsList=new ArrayList<String>();
        //callsList.add("list");
        //callsList.add("view");
        //callsList.add("recordinglist");
        //callsList.add("notifications");
        callsList.add("makecall");
        //callsList.add("modifylivecall");
        
        List<String> smsList=new ArrayList<>();
        smsList.add("list");
        smsList.add("send");
        smsList.add("view");
        
        List<String> incomingNumberList=new ArrayList<String>();
        incomingNumberList.add("list");
        incomingNumberList.add("view");
        incomingNumberList.add("create");
        incomingNumberList.add("update");
        incomingNumberList.add("delete");
        
        List<String> callerIdList = new ArrayList<String>();
        //callerIdList.add("list");
       //callerIdList.add("create");
        //callerIdList.add("view");
        //callerIdList.add("update");
        //callerIdList.add("delete");
        
        List<String> availableNumberList=new ArrayList<String>();
        //availableNumberList.add("locallist");
        //availableNumberList.add("startswith");
        //availableNumberList.add("storm");
        //availableNumberList.add("region");
        //availableNumberList.add("withindistance");
        //availableNumberList.add("tollfreelist");
        //availableNumberList.add("tollfreestorm");
        availableNumberList.add("tollfreepattern");
        
        List<String> accountList = new ArrayList<String>();
       // accountList.add("view");
        //accountList.add("create");
        //accountList.add("suspend");
       // accountList.add("active");
        //accountList.add("close");
        //accountList.add("list");
        //accountList.add("listactive");
        
        /*for (String activity : accountList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(account, activity, TWILIO);
			if (account.equalsIgnoreCase("account")
					&& activity.equalsIgnoreCase("view")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
        for (String activity : availableNumberList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(availableNumbers, activity, TWILIO);
			if (availableNumbers.equalsIgnoreCase("availablenumbers")
					&& activity.equalsIgnoreCase("locallist")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
        for (String activity : callerIdList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(callerId, activity, TWILIO);
			if (callerId.equalsIgnoreCase("availablenumbers")
					&& activity.equalsIgnoreCase("list")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
        for (String activity : incomingNumberList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(incomingNumber, activity, TWILIO);
			if (incomingNumber.equalsIgnoreCase("callerid")
					&& activity.equalsIgnoreCase("list")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
        for (String activity : smsList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(sms, activity, TWILIO);
			if (sms.equalsIgnoreCase("sms")
					&& activity.equalsIgnoreCase("list")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
        for (String activity : callsList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(calls, activity, TWILIO);
			if (calls.equalsIgnoreCase("calls")
					&& activity.equalsIgnoreCase("list")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}*/
        for (String activity : appsList) {
			CommonTest ctest = new CommonTest();
			RequestData reqData;
			reqData = ctest.commonTest(application, activity, TWILIO);
			if (application.equalsIgnoreCase("application")
					&& activity.equalsIgnoreCase("create")) {
				testAdapterAccess(reqData);
			}
			ctest.testBusinessImpl();
		}
	}
	
	private void testAdapterAccess(RequestData reqData) {

		TwilioAdapterAccess taa = new TwilioAdapterAccess();
		try {
			DataMap dmap = taa.authenticate(reqData.getGeneral());
		} catch (AdapterAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
