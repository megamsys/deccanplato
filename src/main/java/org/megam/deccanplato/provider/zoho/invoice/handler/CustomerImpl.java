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
package org.megam.deccanplato.provider.zoho.invoice.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.zoho.invoice.Constants.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.zoho.invoice.info.Contact;
import org.megam.deccanplato.provider.zoho.invoice.info.InvoiceUpdate;
import org.megam.deccanplato.provider.zoho.invoice.info.CustomFields;
import org.megam.deccanplato.provider.zoho.invoice.info.Customer;

/**
 * @author pandiyaraja
 *
 *this class deals with customer creation, deletion, update and list 
 *from zoho invoice. this class takes input from args Map.
 *this class uses three other classes customer, contact and customfield
 *to develop xml data. 
 */
public class CustomerImpl implements BusinessActivity{

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;
		
	}

	@Override
	public Map<String, String> run() {
		Map<String, String> outMap=new HashMap<>();
		switch (bizInfo.getActivityFunction()) {
		case CREATE:
			outMap=create(outMap);
			break;
		case LIST:
			outMap=list(outMap);
			break;
		case UPDATE:
			outMap=update(outMap);
			break;
		case DELETE:
			outMap=delete(outMap);
			break;
		case VIEW:
			outMap=view(outMap);
			break;
		default:
			break;
		}
		return outMap;
	}

	/**
	 * this business method shows a particular customer details.
	 * @param outMap
	 * @return customer details in xml formate
	 */
	private Map<String, String> view(Map<String, String> outMap) {
		final String ZOHO_INVOICE_CUSTOMER_LIST_URL = "https://invoice.zoho.com/api/customers/"+args.get(ID);
		
		
		List<NameValuePair> accountAttrList=new ArrayList<NameValuePair>();
		accountAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		accountAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		accountAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
        
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_CUSTOMER_LIST_URL, accountAttrList, null, true, "UTF-8");
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.get(tst);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseBody = response.entityToString();

		outMap.put(OUTPUT, responseBody);
		return outMap;		
	}

	/**
	 * this method creates a customer in zoho invoice and returns that customer details.
	 * and it uses the business support classes Customer, Contact and CustomFields to populate ZOHO XML input
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_CUSTOMER_CREATE_URL = "https://invoice.zoho.com/api/view/customers/create";
		
		Customer customer=new Customer();
		Contact contact=new Contact();
		CustomFields custom=new CustomFields();
		customer.setBillingAddress(args.get(BILLING_ADDESS));
		customer.setBillingCity(args.get(BILLING_CITY));
		customer.setBillingCountry(args.get(BILLING_COUNTRY));
		customer.setBillingFax(args.get(BILLING_FAX));
		customer.setBillingZip(args.get(BILLING_ZIP));
		customer.setCurrencyCode(args.get(CURRENCY_CODE));
		customer.setName(args.get(NAME));
		customer.setPaymentsDue(args.get(PAYMENT_DUE));
		customer.setShippingAddress(args.get(SHIPPING_ADDRESS));
		customer.setShippingCity(args.get(SHIPPING_CITY));
		customer.setShippingCountry(args.get(SHIPPING_COUNTRY));
		customer.setShippingFax(args.get(SHIPPING_FAX));
		customer.setShippingState(args.get(SHIPPING_STATE));
		customer.setShippingZip(args.get(SHIPPING_ZIP));
		contact.setEmail(args.get(EMAIL));
		contact.setFirstName(args.get(NAME));
		contact.setLastName(args.get(NAME));
		contact.setMobile(args.get(MOBILE));
		contact.setPhone(args.get(PHONE));
		contact.setSalutation(args.get(SALUTATION));
		customer.getContact().add(contact);
		custom.setField1_name(args.get(LABEL1));
		custom.setField1_value(args.get(VALUE1));
		custom.setField2_name(args.get(LABEL2));
		custom.setField2_value(args.get(VALUE2));
		custom.setField3_name(args.get(LABEL3));
		custom.setField3_value(args.get(VALUE3));
		customer.getCustomFields().add(custom);
		customer.setNotes(args.get(NOTES));
		String xmlout = null;
		try {
			xmlout=customer.toXMLString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<NameValuePair> customerAttrList=new ArrayList<NameValuePair>();
		customerAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		customerAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		customerAttrList.add(new BasicNameValuePair(ZOHO_XMLSTRING, xmlout));
		customerAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
        TransportTools tst = new TransportTools(ZOHO_INVOICE_CUSTOMER_CREATE_URL, customerAttrList);
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.post(tst);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseBody = response.entityToString();

		outMap.put(OUTPUT, responseBody);
		return outMap;
	}

	/**
	 * this method lists all active customer in zoho invoice and returns a list of Customers details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
       final String ZOHO_INVOICE_CUSTOMER_LIST_URL = "https://invoice.zoho.com/api/customers";
		
		
		List<NameValuePair> listAttrList=new ArrayList<NameValuePair>();
		listAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		listAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		listAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
        
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_CUSTOMER_LIST_URL, listAttrList, null, true, "UTF-8");
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.get(tst);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseBody = response.entityToString();

		outMap.put(OUTPUT, responseBody);
		return outMap;		
	}

	/**
	 * this method update a particular customer in zoho invoice and returns customer details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		

final String ZOHO_INVOICE_CUSTOMER_UPDATE_URL = "https://invoice.zoho.com/api/view/customers/update";
		
	Customer customer=new Customer();
	Contact contact=new Contact(Boolean.parseBoolean(args.get(DELETE_CONTACT)));
	CustomFields custom=new CustomFields();
	
	
	customer.setCustomerId(args.get(CUSTOMERID));
	customer.setBillingAddress(args.get(BILLING_ADDESS));
	customer.setBillingCity(args.get(BILLING_CITY));
	customer.setBillingCountry(args.get(BILLING_COUNTRY));
	customer.setBillingFax(args.get(BILLING_FAX));
	customer.setBillingZip(args.get(BILLING_ZIP));
	customer.setPaymentsDue(args.get(PAYMENT_DUE));
	customer.setShippingAddress(args.get(SHIPPING_ADDRESS));
	customer.setShippingCity(args.get(SHIPPING_CITY));
	customer.setShippingCountry(args.get(SHIPPING_COUNTRY));
	customer.setShippingFax(args.get(SHIPPING_FAX));
	customer.setShippingState(args.get(SHIPPING_STATE));
	customer.setShippingZip(args.get(SHIPPING_ZIP));	
	contact.setContactId(args.get(CONTACT_ID));
	contact.setSalutation(args.get(SALUTATION));
	contact.setEmail(args.get(EMAIL));
	contact.setFirstName(args.get(FIRST_NAME));
	contact.setLastName(args.get(LAST_NAME));
	contact.setPhone(args.get(PHONE));
	customer.getContact().add(contact);
	custom.setField1_name(LABEL1);
	custom.setField1_value(args.get(VALUE1));
	custom.setField2_name(args.get(LABEL2));
	custom.setField2_value(args.get(VALUE2));
	custom.setField3_name(args.get(LABEL3));
	custom.setField3_value(args.get(VALUE3));
	customer.getCustomFields().add(custom);
	String xmlout = null;
	try {
		xmlout=customer.toXMLString();
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		List<NameValuePair> updateAttrList=new ArrayList<NameValuePair>();
		updateAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		updateAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		updateAttrList.add(new BasicNameValuePair(ZOHO_XMLSTRING, xmlout));
		updateAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
		
        TransportTools tst = new TransportTools(ZOHO_INVOICE_CUSTOMER_UPDATE_URL, updateAttrList);
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.post(tst);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseBody = response.entityToString();

		outMap.put(OUTPUT, responseBody);
		return outMap;
		
	}

	/**
	 * this method delete a particular customer in zoho invoice and returns Status.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_CUSTOMER_DELETE_URL="https://invoice.zoho.com/api/customers/delete/"+args.get(ID);
		
		List<NameValuePair> deleteAttrList=new ArrayList<NameValuePair>();
		deleteAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		deleteAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		deleteAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
		TransportTools tst = new TransportTools(ZOHO_INVOICE_CUSTOMER_DELETE_URL, deleteAttrList);
		String responseBody = null;

		TransportResponse response = null;

		try {
			response = TransportMachinery.post(tst);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseBody = response.entityToString();

		outMap.put(OUTPUT, responseBody);
		return outMap;		
	}

	/**
     * this method returns business method name to perform action in that zoho invoice Module 
     */
	@Override
	public String name() {
		return "customer";
	}
}
