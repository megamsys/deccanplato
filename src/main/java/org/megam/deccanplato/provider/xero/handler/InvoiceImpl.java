/* “Copyright 2012 Megam Systems”
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.megam.deccanplato.provider.xero.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.xero.Constants.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.oauth.OAuthMessage;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.rossjourdain.util.xero.Address;
import com.rossjourdain.util.xero.ArrayOfAddress;
import com.rossjourdain.util.xero.ArrayOfInvoice;
import com.rossjourdain.util.xero.ArrayOfLineItem;
import com.rossjourdain.util.xero.ArrayOfPhone;
import com.rossjourdain.util.xero.Contact;
import com.rossjourdain.util.xero.Invoice;
import com.rossjourdain.util.xero.InvoiceStatus;
import com.rossjourdain.util.xero.InvoiceType;
import com.rossjourdain.util.xero.LineItem;
import com.rossjourdain.util.xero.Phone;
import com.rossjourdain.util.xero.ResponseType;
import com.rossjourdain.util.xero.XeroClientException;
import com.rossjourdain.util.xero.XeroClientUnexpectedException;
import com.rossjourdain.util.xero.XeroPublicClient;
import com.rossjourdain.util.xero.XeroXmlManager;
/**
 * 
 * @author pandiyaraja
 *
 * This class implements Xero invoice business functionalities,
 * this class performs create, update and list invoice details.
 */
public class InvoiceImpl implements BusinessActivity {

	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo, Map<String, String> tempArgs) {
		this.args = tempArgs;
		this.bizInfo = tempBizInfo;		
		
	}

	@Override
	public Map<String, String> run() {
		Map<String, String> outMap= null;
		
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
		case UPDATE:
			/**
			 * RP-TODO : Will this work ? Why do we need two methods create() and update()
			 */
			outMap=create();
			break;
		}
		return outMap;
	}
	
	/**
	 * This method lists all invoice account from xero, it returns
	 * all details about all invoice items.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> listAll() {
		Map<String,String> outMap = new HashMap<String,String>();	
		
		try {
			XeroPublicClient client=new XeroPublicClient(args);
			String responseString =client.listAll(new StringTokenizer(args.get(BIZ_FUNCTION), "#").nextToken());
			outMap.put(OUTPUT, responseString);
		} catch (XeroClientException e) {
			e.printStackTrace();
		} catch (XeroClientUnexpectedException e) {
			e.printStackTrace();
		} 
		return outMap;
		
	}
	
	/**
	 * This method return a particular invoice detail by
	 * using invoice id.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list() {
		Map<String,String> outMap = new HashMap<String,String>();	
		
		try {
			XeroPublicClient client=new XeroPublicClient(args);
        	String responseString =client.list(args.get(ID), 
        			new StringTokenizer(args.get(BIZ_FUNCTION), "#").nextToken());
    		outMap.put(OUTPUT, responseString);
		} catch (XeroClientException e) {
			e.printStackTrace();
		} catch (XeroClientUnexpectedException e) {
			e.printStackTrace();
		} 
		return outMap; 
	}

	
	/**
	 * This method creates an invoices in xero,
	 * It uses some sub classes like contact, invoice, address,
	 * phone and line item then it convert to xml format data and 
	 * send to xero public client to create an invoice account. 
	 * @param outMap
	 * @return
	 */
	private Map<String, String> create() {		
		XeroPublicClient client=new XeroPublicClient(args);
		Map<String,String> outMap = new HashMap<String, String>();
		
        try {

            ArrayOfInvoice arrayOfInvoice = new ArrayOfInvoice();
            List<Invoice> invoices = arrayOfInvoice.getInvoice();
            Invoice invoice = new Invoice();

            Contact contact = new Contact();
            //contact.setContactID(args.get(CONTACT_ID));
            //contact.setContactNumber(args.get(CONTACT_NUMBER));
            contact.setName(args.get(NAME));
            contact.setEmailAddress(args.get(EMAIL_ID));            
             
            ArrayOfAddress arrayOfAddress=new ArrayOfAddress();
            List<Address> addresses=arrayOfAddress.getAddress();
            Address address=new Address();
            address.setAttentionTo(args.get(TO_NAME));
            address.setCity(args.get(CITY));
            address.setCountry(args.get(COUNTRY));
            address.setPostalCode(POSTAL_CODE);
            address.setRegion(args.get(REGION));
            addresses.add(address);
            contact.setAddresses(arrayOfAddress);
            
            ArrayOfPhone arrayOfPhone=new ArrayOfPhone();
            List<Phone> phones = arrayOfPhone.getPhone();
            Phone phone=new Phone();
            phone.setPhoneNumber(args.get(PHONE_NO));
            phone.setPhoneCountryCode(args.get(COUNTRY_CODE));
            phone.setPhoneAreaCode(args.get(AREA_CODE));
            phones.add(phone);
            contact.setPhones(arrayOfPhone);
            invoice.setContact(contact);
            
            ArrayOfLineItem arrayOfLineItem = new ArrayOfLineItem();
            List<LineItem> lineItems = arrayOfLineItem.getLineItem();
            LineItem lineItem = new LineItem();
            lineItem.setAccountCode(args.get(ACCOUNT_CODE));
            BigDecimal qty = new BigDecimal(args.get(QUANTITY));
            lineItem.setQuantity(qty);
            BigDecimal amnt = new BigDecimal(args.get(AMOUNT));
            lineItem.setUnitAmount(amnt);
            lineItem.setTaxType(args.get(TAX_TYPE));
            lineItem.setDescription(args.get(DESCRIPTION));
            lineItem.setLineAmount(qty.multiply(amnt));
            lineItems.add(lineItem);
            invoice.setLineItems(arrayOfLineItem);

            invoice.setDate(Calendar.getInstance());
            Calendar due = Calendar.getInstance();
            due.set(due.get(Calendar.YEAR), due.get(Calendar.MONTH) + 1, 20);
            invoice.getLineAmountTypes().add(args.get(LINE_AMOUNT_TYPE));
            invoice.setDueDate(due);
            invoice.setInvoiceNumber(args.get(INVOICE_NUMBER));
            invoice.setType(InvoiceType.ACCREC);
            invoice.setStatus(InvoiceStatus.AUTHORISED);
            invoice.setInvoiceID(args.get(ID));
            invoices.add(invoice);
            /**
             * RP-TODO, does a post return anything ? If it doesn't then remove the 
             * concat of CREATE_STRING
             */
            String responseString = client.post(XeroXmlManager.invoicesToXml(arrayOfInvoice), 
            		new StringTokenizer(args.get(BIZ_FUNCTION), "#").nextToken());
            outMap.put(OUTPUT, CREATE_STRING +"/n" + responseString);
        } catch (XeroClientException ex) {
            ex.printDetails();
        } catch (XeroClientUnexpectedException ex) {
            ex.printStackTrace();
        }
		return outMap;		
	}
		

	/**
	 * RP-TODO, Why is this method needed  ?
	 * 
	 * This method updates an invoices in xero,
	 * It uses some sub classes like contact, invoice, address,
	 * phone and line item then it convert to xml format data and 
	 * send to xero public client to create an invoice account. 
	 * post and put both can use to update an invoice. 
	 * @param outMap
	 * @return
	 
	private Map<String, String> update() {
		XeroPublicClient client=new XeroPublicClient(args);
        try {
		StringTokenizer stok=new StringTokenizer(args.get(BIZ_FUNCTION), "#");

            ArrayOfInvoice arrayOfInvoice = new ArrayOfInvoice();
            List<Invoice> invoices = arrayOfInvoice.getInvoice();
            Invoice invoice = new Invoice();            
            
            Contact contact = new Contact();
            //contact.setContactID(args.get(CONTACT_ID));
            //contact.setContactNumber(args.get(CONTACT_NUMBER));
            contact.setName(args.get(NAME));
            contact.setEmailAddress(args.get(EMAIL_ID));            
             
            ArrayOfAddress arrayOfAddress=new ArrayOfAddress();
            List<Address> addresses=arrayOfAddress.getAddress();
            Address address=new Address();
            address.setAttentionTo(args.get(TO_NAME));
            address.setCity(args.get(CITY));
            address.setCountry(args.get(COUNTRY));
            address.setPostalCode(POSTAL_CODE);
            address.setRegion(args.get(REGION));
            addresses.add(address);
            contact.setAddresses(arrayOfAddress);
            
            ArrayOfPhone arrayOfPhone=new ArrayOfPhone();
            List<Phone> phones = arrayOfPhone.getPhone();
            Phone phone=new Phone();
            phone.setPhoneNumber(args.get(PHONE_NO));
            phone.setPhoneCountryCode(args.get(COUNTRY_CODE));
            phone.setPhoneAreaCode(args.get(AREA_CODE));
            phones.add(phone);
            contact.setPhones(arrayOfPhone);
            invoice.setContact(contact);
            
            ArrayOfLineItem arrayOfLineItem = new ArrayOfLineItem();
            List<LineItem> lineItems = arrayOfLineItem.getLineItem();
            LineItem lineItem = new LineItem();
            lineItem.setAccountCode(args.get(ACCOUNT_CODE));
            BigDecimal qty = new BigDecimal(args.get(QUANTITY));
            lineItem.setTaxType(args.get(TAX_TYPE));
            lineItem.setQuantity(qty);
            BigDecimal amnt = new BigDecimal(args.get(AMOUNT));
            lineItem.setUnitAmount(amnt);
            lineItem.setDescription(args.get(DESCRIPTION));
            lineItem.setLineAmount(qty.multiply(amnt));
            lineItems.add(lineItem);
            invoice.setLineItems(arrayOfLineItem);

            invoice.setDate(Calendar.getInstance());
            Calendar due = Calendar.getInstance();
            due.set(due.get(Calendar.YEAR), due.get(Calendar.MONTH) + 1, 20);
            invoice.getLineAmountTypes().add(args.get(LINE_AMOUNT_TYPE));
            invoice.setDueDate(due);
            invoice.setInvoiceNumber(args.get(INVOICE_NUMBER));
            invoice.setType(InvoiceType.ACCREC);
            invoice.setStatus(InvoiceStatus.AUTHORISED);
            invoices.add(invoice);

            OAuthMessage msg=client.postXero(XeroXmlManager.invoicesToXml(arrayOfInvoice), stok.nextToken());
            System.out.println(msg.toString());
        } catch (XeroClientException ex) {
            ex.printDetails();
        } catch (XeroClientUnexpectedException ex) {
            ex.printStackTrace();
        }
        outMap.put(OUTPUT, UPDATE_STRING);
		return outMap;
		
	}
*/
	
	
	

	@Override
	public String name() {
		return "invoice";
	}

}
