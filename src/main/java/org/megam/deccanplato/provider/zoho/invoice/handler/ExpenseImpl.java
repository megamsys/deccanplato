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
import org.megam.deccanplato.provider.zoho.invoice.info.Expense;

/**
 * @author pandiyaraja
 *
 *this class implements zoho invoice expense business function
 *this class has four business methods that are create, list, update
 *and delete.
 */
public class ExpenseImpl implements BusinessActivity{

	private static final String ZOHO_INVOICE_EXPENSE="https://invoice.zoho.com/api/expenses/";
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
		default:
			break;
		}
		return outMap;
	}

	/**
	 * this method creates an espense in zoho invoice and returns that expense details.
	 * and it uses the business support class Expense to populate ZOHO XML input
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_EXPENSE_CREATE_URL = ZOHO_INVOICE_EXPENSE+"create";
		Expense expense=new Expense();
		expense.setCategoryName(args.get(CATEGORY_NAME));
		expense.setExpenseDate(args.get(EXPENSE_DATE));
		expense.setExpenseAmount(args.get(AMOUNT));
		expense.setExpenseDescription(args.get(DESCRIPTION));
		expense.setTax1Name(args.get(TAX_NAME_1));
		expense.setIsInclusiveTax(args.get(INCLUSIVE_TAX));
		expense.setIsBillable(args.get(BILLABLE));
		expense.setReference(args.get(REFERENCE));
		expense.setCustomerID(args.get(CUSTOMERID));
		expense.setCurrencyCode(args.get(CURRENCY_CODE));
		expense.setExchangeRate(args.get(EXCHANGE_RATE));
		String xmlout = null;
		try {
			xmlout=expense.toXMLString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<NameValuePair> createAttrList=new ArrayList<NameValuePair>();
		createAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		createAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		createAttrList.add(new BasicNameValuePair(ZOHO_XMLSTRING, xmlout));
		createAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
        TransportTools tst = new TransportTools(ZOHO_INVOICE_EXPENSE_CREATE_URL, createAttrList);
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
	 * this method lists all expense in zoho invoice and returns a list of expense details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
		
		List<NameValuePair> listAttrList=new ArrayList<NameValuePair>();
		listAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		listAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		listAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
        
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_EXPENSE, listAttrList, null, true, "UTF-8");
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
	 * this method update a particular expense in zoho invoice and returns updated expense details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		

		final String ZOHO_INVOICE_EXPENSE_UPDATE_URL = ZOHO_INVOICE_EXPENSE+"update";
		
		Expense expense=new Expense();
		expense.setExpenseID(args.get(ID));
		expense.setCategoryName(args.get(CATEGORY_NAME));
		expense.setExpenseDate(args.get(EXPENSE_DATE));
		expense.setExpenseAmount(args.get(AMOUNT));
		expense.setExpenseDescription(args.get(DESCRIPTION));
		expense.setTax1Name(args.get(TAX_NAME_1));
		expense.setIsInclusiveTax(args.get(INCLUSIVE_TAX));
		expense.setIsBillable(args.get(BILLABLE));
		expense.setReference(args.get(REFERENCE));
		expense.setCustomerID(args.get(CUSTOMERID));
		expense.setCurrencyCode(args.get(CURRENCY_CODE));
		expense.setExchangeRate(args.get(EXCHANGE_RATE));
		String xmlout = null;
		try {
			xmlout=expense.toXMLString();
		} catch (JAXBException e) {
			// 	TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<NameValuePair> updateAttrList=new ArrayList<NameValuePair>();
		updateAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		updateAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		updateAttrList.add(new BasicNameValuePair(ZOHO_XMLSTRING, xmlout));
		updateAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
		
        TransportTools tst = new TransportTools(ZOHO_INVOICE_EXPENSE_UPDATE_URL, updateAttrList);
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
	 * this method delete a particular expense in zoho invoice and returns status code.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_EXPENSE_DELETE_URL=ZOHO_INVOICE_EXPENSE+"delete/"+args.get(ID);
		
		List<NameValuePair> deleteAttrList=new ArrayList<NameValuePair>();
		deleteAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		deleteAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		deleteAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
		TransportTools tst = new TransportTools(ZOHO_INVOICE_EXPENSE_DELETE_URL, deleteAttrList);
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
		return "expense";
	}
}
