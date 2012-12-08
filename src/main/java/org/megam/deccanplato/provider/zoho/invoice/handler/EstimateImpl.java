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

/**
 * @author pandiyaraja
 *this class deals with zoho invoice Estimates
 *this class implements create, list, update, delete, send, mark and pdf
 *business methods.
 */
public class EstimateImpl implements BusinessActivity{

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
		case MARK:
			outMap=mark(outMap);
			break;
		case PDF:
			outMap=pdf(outMap);
			break;
		default:
			break;
		}
		return outMap;
	}

	/**
	 * this method exports a particular estimate details to pdf	
	 * @param outMap id.
	 * @return pdf file.
	 */
	private Map<String, String> pdf(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_ESTIMATE_PDF_URL = "https://invoice.zoho.com/api/estimates/pdf";		
		
		List<NameValuePair> pdfAttrList=new ArrayList<NameValuePair>();
		pdfAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		pdfAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		pdfAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		pdfAttrList.add(new BasicNameValuePair("EstimateID", args.get(ID)));        
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_ESTIMATE_PDF_URL, pdfAttrList, null, true, "UTF-8");
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
	 * this business method marks a particular estimate as sent
	 * @param outMap
	 * @return status code
	 */
	private Map<String, String> mark(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_ESTIMATE_MARK_URL = "https://invoice.zoho.com/api/estimates/markassent/"+args.get(ID);		
		
		List<NameValuePair> markAttrList=new ArrayList<NameValuePair>();
		markAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		markAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		markAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		      
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_ESTIMATE_MARK_URL, markAttrList, null, true, "UTF-8");
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
	 * this business method send estimate details through email
	 * to a particular mailers
	 * @param outMap
	 * @return return a success message and response code
	 */
	private Map<String, String> send(Map<String, String> outMap) {
			
		final String ZOHO_INVOICE_ESTIMATE_LIST_URL = "https://invoice.zoho.com/api/estimates/send";
		
		
		List<NameValuePair> sendAttrList=new ArrayList<NameValuePair>();
		sendAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		sendAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		sendAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		sendAttrList.add(new BasicNameValuePair("Custom.Subject", args.get(CUSTOM_SUBJECT)));
		sendAttrList.add(new BasicNameValuePair("Custom.Body", args.get(CUSTOM_BODY)));
		sendAttrList.add(new BasicNameValuePair("EstimateID", args.get(ID)));
		sendAttrList.add(new BasicNameValuePair("ToEMailIDs", args.get(TO_EMAIL)));
		sendAttrList.add(new BasicNameValuePair("CCEMailIDs", args.get(CC_EMAIL)));
        
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_ESTIMATE_LIST_URL, sendAttrList, null, true, "UTF-8");
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
	 * this method creates an Estimate in zoho invoice and returns that users details.
	 * and it uses the business support class Estimate, EstimateItem and Comment to populate ZOHO ESTIMATE XML input
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_ESTIMATE_CREATE_URL = "https://invoice.zoho.com/api/view/estimates/create";
		
		Estimate estimate=new Estimate();
		EstimateItem estimateitem=new EstimateItem();
		Comment comment=new Comment();
		estimate.setCustomerID(args.get(CUSTOMERID));
		estimate.setEstimateDate(args.get(ESTIMATE_DATE));
		estimate.setReferenceNumber(args.get(REFERENCE_NO));
		estimate.setExchangeRate(args.get(EXCHANGE_RATE));
		estimate.setCustom_Body(args.get(CUSTOM_BODY));
		estimate.setCustom_Subject(args.get(CUSTOM_SUBJECT));
		estimateitem.setProductID(args.get(PRODUCT_ID));
		estimate.getEstimateItem().add(estimateitem);
		comment.setDescription(args.get(DESCRIPTION));
		estimate.getComment().add(comment);
		estimate.setNotes(args.get(NOTES));
		estimate.setTerms(args.get(TERMS));
		String xmlout = null;
		try {
			xmlout=estimate.toXMLString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<NameValuePair> createAttrList=new ArrayList<NameValuePair>();
		createAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		createAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		createAttrList.add(new BasicNameValuePair(ZOHO_XMLSTRING, xmlout));
		createAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
        TransportTools tst = new TransportTools(ZOHO_INVOICE_ESTIMATE_CREATE_URL, createAttrList);
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
	 * this method lists all estimates in zoho invoice and returns a list of json estimate details.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
       final String ZOHO_INVOICE_ESTIMATE_LIST_URL = "https://invoice.zoho.com/api/estimates/";
		
		
		List<NameValuePair> listAttrList=new ArrayList<NameValuePair>();
		listAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		listAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		listAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
        
       
        TransportTools tst = new TransportTools(ZOHO_INVOICE_ESTIMATE_LIST_URL, listAttrList, null, true, "UTF-8");
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
	 * this method update a particular estimate in zoho invoice and returns success message with updated estimate.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		

		final String ZOHO_INVOICE_ESTIMATE_UPDATE_URL = "https://invoice.zoho.com/api/view/estimates/update";
			
		Estimate estimate=new Estimate();
		EstimateItem estimateitem=new EstimateItem(Boolean.parseBoolean(args.get(DELETE_ESTIMATE)));
		Comment comment=new Comment();
		estimate.setEstimateId(args.get(ID));
		estimate.setCustomerID(args.get(CUSTOMERID));
		estimate.setEstimateDate(args.get(ESTIMATE_DATE));
		estimate.setReferenceNumber(args.get(REFERENCE_NO));
		estimate.setExchangeRate(args.get(EXCHANGE_RATE));
		estimate.setCustom_Body(args.get(CUSTOM_BODY));
		estimate.setCustom_Subject(args.get(CUSTOM_SUBJECT));
		estimateitem.setProductID(args.get(PRODUCT_ID));
		estimate.getEstimateItem().add(estimateitem);
		comment.setDescription(args.get(DESCRIPTION));
		estimate.getComment().add(comment);
		estimate.setNotes(args.get(NOTES));
		estimate.setTerms(args.get(TERMS));
		String xmlout = null;
		try {
			xmlout=estimate.toXMLString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<NameValuePair> updateAttrList=new ArrayList<NameValuePair>();
		updateAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		updateAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		updateAttrList.add(new BasicNameValuePair(ZOHO_XMLSTRING, xmlout));
		updateAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
		
        TransportTools tst = new TransportTools(ZOHO_INVOICE_ESTIMATE_UPDATE_URL, updateAttrList);
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
	 * this method delete a particular estimate in zoho invoice and returns status code.
	 * This method takes input as a MAP(contains json dada) and returns a MAP.
	 * @param outMap 
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		final String ZOHO_INVOICE_ESTIMATE_DELETE_URL="https://invoice.zoho.com/api/estimates/delete/"+args.get(ID);
		
		List<NameValuePair> deleteAttrList=new ArrayList<NameValuePair>();
		deleteAttrList.add(new BasicNameValuePair(OAUTH_TOKEN, args.get(AUTHTOKEN)));
		deleteAttrList.add(new BasicNameValuePair(ZOHO_SCOPE, SCOPE));
		deleteAttrList.add(new BasicNameValuePair(APIKEY, args.get(APIKEY)));
		
		TransportTools tst = new TransportTools(ZOHO_INVOICE_ESTIMATE_DELETE_URL, deleteAttrList);
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
		return "estimate";
	}
}
