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
package org.megam.deccanplato.provider.xero.handler;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.rossjourdain.util.xero.Account;
import com.rossjourdain.util.xero.Address;
import com.rossjourdain.util.xero.ArrayOfAddress;
import com.rossjourdain.util.xero.ArrayOfContact;
import com.rossjourdain.util.xero.ArrayOfPayment;
import com.rossjourdain.util.xero.ArrayOfPhone;
import com.rossjourdain.util.xero.Contact;
import com.rossjourdain.util.xero.Invoice;
import com.rossjourdain.util.xero.Payment;
import com.rossjourdain.util.xero.Phone;
import com.rossjourdain.util.xero.PhoneTypeCodeType;
import com.rossjourdain.util.xero.XeroClientException;
import com.rossjourdain.util.xero.XeroClientUnexpectedException;
import com.rossjourdain.util.xero.XeroPublicClient;
import com.rossjourdain.util.xero.XeroXmlManager;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.xero.Constants.*;

/**
 * @author pandiyaraja
 *
 *This class implements Xero invoice Contact module
 *This class creates, updates and lists contact in xero invoices.
 */
public class ContactImpl implements BusinessActivity {

	private BusinessActivityInfo bizInfo;
	private Map<String, String> args;
	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#setArguments(org.megam.deccanplato.provider.core.BusinessActivityInfo, java.util.Map)
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.args=tempArgs;
		this.bizInfo=tempBizInfo;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#run()
	 */
	@Override
	public Map<String, String> run() {
		Map<String, String> outMap=null;
		switch(bizInfo.getActivityFunction()) {
		case LIST:
			outMap=listAll();
			break;
		case VIEW:
			outMap=list();
			break;
		case CREATE:
			outMap=create();
			break;
		}
		return outMap;
	}

	/**
	 * this method use to create and update contact in Xero. 
	 * It uses some some external classes like Phone and Address
	 * the data is converted to xml format and send to xero to create 
	 * a contact.
	 * @return
	 */
	private Map<String, String> create() {
		Map<String, String> outMap=new HashMap<>();
		XeroPublicClient client=new XeroPublicClient(args);
		try {
            ArrayOfContact arrayOfContact=new ArrayOfContact();
            List<Contact> contacts=arrayOfContact.getContact();
            Contact contact=new Contact();
            
            if(args.get(CONTACT_ID)!=null) {
            	contact.setContactID(args.get(CONTACT_ID));
            }
            ArrayOfAddress arrayOfAddress=new ArrayOfAddress();
            List<Address> addresses=arrayOfAddress.getAddress();
            Address address=new Address();
            address.setAttentionTo(args.get(TO_NAME));
            address.setAddressLine1(args.get(ADDRESS_LINE));
            address.setCity(args.get(CITY));
            address.setCountry(args.get(COUNTRY));
            address.setPostalCode(args.get(POSTAL_CODE));
            address.setRegion(args.get(REGION));
            addresses.add(address);
            contact.setAddresses(arrayOfAddress);
            
            ArrayOfPhone arrayOfPhone=new ArrayOfPhone();
            List<Phone> phones= arrayOfPhone.getPhone();
            Phone phone=new Phone();
            phone.setPhoneType(PhoneTypeCodeType.MOBILE);
            phone.setPhoneAreaCode(args.get(AREA_CODE));
            phone.setPhoneCountryCode(args.get(COUNTRY_CODE));
            phone.setPhoneNumber(args.get(PHONE_NO));
            phones.add(phone);
            
            contact.setPhones(arrayOfPhone);
            contact.setName(args.get(NAME));
            contact.setLastName(args.get(LAST_NAME));
            contact.setEmailAddress(args.get(EMAIL_ID));
            contact.setBankAccountDetails(args.get(BANK_DETAILS));
            contact.setFirstName(args.get(FIRST_NAME));
            contact.setContactNumber(args.get(CONTACT_NUMBER));
            contact.setContactStatus(args.get(STATUS));
            contacts.add(contact);
            String response =client.post(XeroXmlManager.contactsToXml(arrayOfContact), 
            		new StringTokenizer(args.get(BIZ_FUNCTION), "#").nextToken());
            outMap.put(OUTPUT, response);
        } catch (XeroClientException ex) {
            ex.printDetails();
        } catch (XeroClientUnexpectedException ex) {
            ex.printStackTrace();
        }
		return outMap;
	}

	/**
	 * 
	 * This method returns a particular Contact details by using 
	 * contact account id.
	 */
	private Map<String, String> list() {
		Map<String,String> outMap = new HashMap<String,String>();	
		
		try {
			XeroPublicClient client=new XeroPublicClient(args);
        	String responseString =client.list(args.get(ID), 
        			new StringTokenizer(args.get(BIZ_FUNCTION), "#").nextToken());
        	System.out.println(responseString);
        	System.out.println(args.get(BIZ_FUNCTION));
    		outMap.put(OUTPUT, responseString);
		} catch (XeroClientException e) {
			e.printStackTrace();
		} catch (XeroClientUnexpectedException e) {
			e.printStackTrace();
		} 
		return outMap;
	}

	/**
	 * This method returns a list of contacts from xero
	 * @return list of contacts.
	 */
	private Map<String, String> listAll() {
		 Map<String,String> outMap = new HashMap<String,String>();	
			
			try {
				XeroPublicClient client=new XeroPublicClient(args);
	        	String response =client.listAll(new StringTokenizer(args.get(BIZ_FUNCTION), "#").nextToken());
	    		outMap.put(OUTPUT, response);
	    		System.out.println(response);
			} catch (XeroClientException e) {
				e.printStackTrace();
			} catch (XeroClientUnexpectedException e) {
				e.printStackTrace();
			} 
			return outMap;  
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		return "contact";
	}

}
