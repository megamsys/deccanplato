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

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.xero.Constants.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.rossjourdain.util.xero.ArrayOfItem;
import com.rossjourdain.util.xero.ArrayOfLineItem;
import com.rossjourdain.util.xero.ArrayOfReceipt;
import com.rossjourdain.util.xero.Contact;
import com.rossjourdain.util.xero.Item;
import com.rossjourdain.util.xero.ItemPriceDetails;
import com.rossjourdain.util.xero.LineItem;
import com.rossjourdain.util.xero.Receipt;
import com.rossjourdain.util.xero.ReceiptStatus;
import com.rossjourdain.util.xero.User;
import com.rossjourdain.util.xero.XeroClientException;
import com.rossjourdain.util.xero.XeroClientUnexpectedException;
import com.rossjourdain.util.xero.XeroPublicClient;
import com.rossjourdain.util.xero.XeroXmlManager;

/**
 * @author pandiyaraja
 * 
 * This class performs xero receipts function and this class
 * creates and updates receipts in xero, then list all receipts and
 * view a receipt
 */
public class ReceiptsImpl implements BusinessActivity {
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
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
		case UPDATE:
			outMap=create();
			break;
		}
		return outMap;
	}

	/**
	 * This method creates and updates a receipts in xero 
	 * update can perform by using receipts id.
	 * @return
	 */
	private Map<String, String> create() {
		Map<String, String> outMap=new HashMap<>();
		XeroPublicClient client=new XeroPublicClient(args);
		try {
			ArrayOfReceipt arrayOfReceipt=new ArrayOfReceipt();
			List<Receipt> receipts=arrayOfReceipt.getReceipt();
			Receipt receipt=new Receipt();			
			
			receipt.setDate(Calendar.getInstance());
			Contact contact=new Contact();
			contact.setContactID(args.get(CONTACT_ID));
			receipt.setContact(contact);
			receipt.getLineAmountTypes().add(args.get(LINE_AMOUNT_TYPE));
			ArrayOfLineItem arrayOfLineItem =new ArrayOfLineItem();
			List<LineItem> lineitems=arrayOfLineItem.getLineItem();
			LineItem lineItem=new LineItem();
			BigDecimal qty=new BigDecimal(args.get(QUANTITY));
			lineItem.setQuantity(qty);
			lineItem.setDescription(args.get(DESCRIPTION));
			lineItem.setAccountCode(args.get(ACCOUNT_CODE));
			BigDecimal amt=new BigDecimal(args.get(UNIT_AMOUNT));
			lineItem.setUnitAmount(amt);
			lineItem.setLineAmount(qty.multiply(amt));
			lineitems.add(lineItem);
			receipt.setLineItems(arrayOfLineItem);
			
			User user=new User();
			user.setUserID(args.get(USER_ID));
			receipt.setUser(user);
			
			
			if(args.get(RECEIPT_ID)!=null) {
				receipt.setReceiptID(args.get(RECEIPT_ID));
			}
			receipts.add(receipt);
			
            String response =client.post(XeroXmlManager.receiptsToXml(arrayOfReceipt), 
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
	 * this method returns a particular receipt details from xero
	 * by using receipt id.
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
	 * this method returns a list of receipts details from xero.
	 * @return
	 */
	private Map<String, String> listAll() {
		Map<String,String> outMap = new HashMap<String,String>();	
		
		try {
			XeroPublicClient client=new XeroPublicClient(args);
        	String response =client.listAll(new StringTokenizer(args.get(BIZ_FUNCTION), "#").nextToken());
    		outMap.put(OUTPUT, response);
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
		return "receipt";
	}

}
