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
import org.megam.deccanplato.provider.zoho.invoice.info.Comment;
import org.megam.deccanplato.provider.zoho.invoice.info.Contact;
import org.megam.deccanplato.provider.zoho.invoice.info.InvoiceUpdate;
import org.megam.deccanplato.provider.zoho.invoice.info.CustomFields;
import org.megam.deccanplato.provider.zoho.invoice.info.Customer;
import org.megam.deccanplato.provider.zoho.invoice.info.Estimate;
import org.megam.deccanplato.provider.zoho.invoice.info.EstimateItem;
import org.megam.deccanplato.provider.zoho.invoice.info.Invoice;
import org.megam.deccanplato.provider.zoho.invoice.info.InvoiceItem;
import org.megam.deccanplato.provider.zoho.invoice.info.PaymentGateway;

/**
 * @author pandiyaraja
 *
 *this class implements the invoice business  
 *such as create, list, update, delete, send, pdf, convert and remind
 */
public class InvoiceImpl implements BusinessActivity{

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
		case SEND:
			outMap=send(outMap);
			break;
		case CONVERT:
			outMap=convert(outMap);
			break;
		case PDF:
			outMap=pdf(outMap);
			break;
		case REMIND:
			outMap=remind(outMap);
			break;
		default:
			break;
		}
		return outMap;
	}

	/**
	 * this business method sends a reminder to user by email
	 * @param outMap
	 * @return
	 */
	private Map<String, String> remind(Map<String, String> outMap) {
		final String ZOHO_INVOICE_INVOICE_LIST_URL = "https://invoice.zoho.com/api/invoices/sendreminder";
		
		
		List<NameValuePair> remindAttrList=new ArrayList<NameValuePair>();
		remindAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		remindAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		remindAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		remindAttrList.add(new BasicNameValuePair("Custom.Subject", args.get(CUSTOM_SUBJECT)));
		remindAttrList.add(new BasicNameValuePair("Custom.Body", args.get(CUSTOM_BODY)));
		remindAttrList.add(new BasicNameValuePair("InvoiceID", args.get(ID)));
		remindAttrList.add(new BasicNameValuePair("ToEMailIDs", args.get(TO_EMAIL)));
		remindAttrList.add(new BasicNameValuePair("CCEMailIDs", args.get(CC_EMAIL)));
        
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_INVOICE_LIST_URL, remindAttrList, null, true, "UTF-8");
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
	 * this business method converts a particular invoice details
	 * to pdf content
	 * @param outMap
	 * @return
	 */
	private Map<String, String> pdf(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_INVOICE_LIST_URL = "https://invoice.zoho.com/api/invoices/pdf";		
		
		List<NameValuePair> pdfAttrList=new ArrayList<NameValuePair>();
		pdfAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		pdfAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		pdfAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		pdfAttrList.add(new BasicNameValuePair("InvoiceID", args.get(ID)));        
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_INVOICE_LIST_URL, pdfAttrList, null, true, "UTF-8");
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
	 * this business method convert a particular invoice to open
	 * @param outMap
	 * @return
	 */
	private Map<String, String> convert(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_INVOICE_LIST_URL = "https://invoice.zoho.com/api/invoices/converttoopen/"+args.get(ID);		
		
		List<NameValuePair> convertAttrList=new ArrayList<NameValuePair>();
		convertAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		convertAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		convertAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		      
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_INVOICE_LIST_URL, convertAttrList, null, true, "UTF-8");
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
	 * this business method sends a particular invoice details to mailers by email
	 * @param outMap
	 * @return
	 */
	private Map<String, String> send(Map<String, String> outMap) {
			
		final String ZOHO_INVOICE_INVOICE_LIST_URL = "https://invoice.zoho.com/api/invoices/send";
		
		
		List<NameValuePair> sendAttrList=new ArrayList<NameValuePair>();
		sendAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		sendAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		sendAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		sendAttrList.add(new BasicNameValuePair("Custom.Subject", args.get(CUSTOM_SUBJECT)));
		sendAttrList.add(new BasicNameValuePair("Custom.Body", args.get(CUSTOM_BODY)));
		sendAttrList.add(new BasicNameValuePair("InvoiceID", args.get(ID)));
		sendAttrList.add(new BasicNameValuePair("ToEMailIDs", args.get(TO_EMAIL)));
		sendAttrList.add(new BasicNameValuePair("CCEMailIDs", args.get(CC_EMAIL)));
        
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_INVOICE_LIST_URL, sendAttrList, null, true, "UTF-8");
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
	 * this method creates an invoice in zoho invoice and returns that invoice details.
	 * and it uses the business support class invoice, paymentgateway and invoiceitem to populate ZOHO invoice XML input
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_INVOICE_CREATE_URL = "https://invoice.zoho.com/api/view/invoices/create";
		
		Invoice invoice = new Invoice();
		PaymentGateway paygate=new PaymentGateway();
		InvoiceItem invoiceitem=new InvoiceItem();
		
		invoice.setCustomerID(args.get(CUSTOMERID));
		invoice.setInvoiceDate(args.get(INVOICE_DATE));
		invoice.setPONumber(args.get(PONUMPER));
		invoice.setExchangeRate(args.get(EXCHANGE_RATE));
		invoice.setCustom_Body(args.get(CUSTOM_BODY));
		invoice.setCustom_Subject(args.get(CUSTOM_SUBJECT));
		paygate.setAuthorize_Net(args.get(AUTHORIZE_NET));
		invoice.getPaymentGateways().add(paygate);
		invoiceitem.setProductID(args.get(PRODUCT_ID));
		invoice.getInvoiceItem().add(invoiceitem);
		invoice.setNotes(args.get(NOTES));
		invoice.setTerms(args.get(TERMS));
		
		String xmlout = null;
		try {
			xmlout=invoice.toXMLString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<NameValuePair> createAttrList=new ArrayList<NameValuePair>();
		createAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		createAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		createAttrList.add(new BasicNameValuePair(ZOHO_XMLSTRING, xmlout));
		createAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
        TransportTools tst = new TransportTools(ZOHO_INVOICE_INVOICE_CREATE_URL, createAttrList);
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
	 * this method lists all invoice in zoho invoice and returns a list of invoice details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
       final String ZOHO_INVOICE_INVOICE_LIST_URL = "https://invoice.zoho.com/api/invoices/";
		
		
		List<NameValuePair> listAttrList=new ArrayList<NameValuePair>();
		listAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		listAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		listAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
        
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_INVOICE_LIST_URL, listAttrList, null, true, "UTF-8");
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
	 * this method update a particular invoice in zoho invoice and returns updated invoice details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		

final String ZOHO_INVOICE_INVOICE_UPDATE_URL = "https://invoice.zoho.com/api/view/invoices/update";
		
	Invoice invoice = new Invoice();
	PaymentGateway paygate=new PaymentGateway();
	InvoiceItem invoiceitem=new InvoiceItem(Boolean.parseBoolean(args.get(DELETE_INVOICE)));

	invoice.setInvoiceID(args.get(ID));
	invoice.setCustomerID(args.get(CUSTOMERID));
	invoice.setInvoiceDate(args.get(INVOICE_DATE));
	invoice.setPONumber(args.get(PONUMPER));
	invoice.setExchangeRate(args.get(EXCHANGE_RATE));
	invoice.setCustom_Body(args.get(CUSTOM_BODY));
	invoice.setCustom_Subject(args.get(CUSTOM_SUBJECT));
	paygate.setAuthorize_Net(args.get(AUTHORIZE_NET));
	invoice.getPaymentGateways().add(paygate);
	invoiceitem.setProductID(args.get(PRODUCT_ID));
	invoice.getInvoiceItem().add(invoiceitem);
	invoice.setNotes(args.get(NOTES));
	invoice.setTerms(args.get(TERMS));
	String xmlout = null;
	try {
		xmlout=invoice.toXMLString();
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		List<NameValuePair> updateAttrList=new ArrayList<NameValuePair>();
		updateAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		updateAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		updateAttrList.add(new BasicNameValuePair(ZOHO_XMLSTRING, xmlout));
		updateAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
		
        TransportTools tst = new TransportTools(ZOHO_INVOICE_INVOICE_UPDATE_URL, updateAttrList);
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
	 * this method delete a particular invoice in zoho invoice and returns status code.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_INVOICE_DELETE_URL="https://invoice.zoho.com/api/invoices/delete/"+args.get(ID);
		
		List<NameValuePair> deleteAttrList=new ArrayList<NameValuePair>();
		deleteAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		deleteAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		deleteAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
		TransportTools tst = new TransportTools(ZOHO_INVOICE_INVOICE_DELETE_URL, deleteAttrList);
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
		return "invoice";
	}
}
