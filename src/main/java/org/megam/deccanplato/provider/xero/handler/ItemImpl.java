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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.rossjourdain.util.xero.ArrayOfItem;
import com.rossjourdain.util.xero.Item;
import com.rossjourdain.util.xero.ItemPriceDetails;
import com.rossjourdain.util.xero.XeroClientException;
import com.rossjourdain.util.xero.XeroClientUnexpectedException;
import com.rossjourdain.util.xero.XeroPublicClient;
import com.rossjourdain.util.xero.XeroXmlManager;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.xero.Constants.*;

/**
 * @author pandiyaraja
 *
 * This class deals with item functionalities in xero invoice
 * this class can perform create, update and view a item from xero
 * it also can list all items from xero.
 */
public class ItemImpl implements BusinessActivity {

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
	 * This method performs both create and update an item in xero
	 * update can perform by using item id.
	 * @return
	 */
	private Map<String, String> create() {
		Map<String, String> outMap=new HashMap<>();
		XeroPublicClient client=new XeroPublicClient(args);
		try {
			ArrayOfItem arrayOfItem=new ArrayOfItem();
            List<Item> items=arrayOfItem.getItem();
            Item item = new Item();
            if(args.get(ITEM_ID)!=null) {
              item.setItemID(args.get(ITEM_ID));
            }
            item.setCode(args.get(ITEM_CODE));
            item.setDescription(args.get(DESCRIPTION));
                       
            ItemPriceDetails itemPriceDetails=new ItemPriceDetails();
            
            if(args.get(SALES_UNIT_PRICE)!=null) {
            	itemPriceDetails.setAccountCode(args.get(ACCOUNT_CODE));
               itemPriceDetails.setTaxType(args.get(TAX_TYPE));
               itemPriceDetails.setUnitPrice(new BigDecimal(args.get(SALES_UNIT_PRICE)));
               item.setSalesDetails(itemPriceDetails);
            }
            else {
            	itemPriceDetails.setAccountCode(args.get(ACCOUNT_CODE));
            	itemPriceDetails.setTaxType(args.get(TAX_TYPE));
            	itemPriceDetails.setUnitPrice(new BigDecimal(args.get(PURCHASE_UNIT_PRICE)));
            	item.setPurchaseDetails(itemPriceDetails);
            }
            items.add(item);          
            
            String response =client.post(XeroXmlManager.itemsToXml(arrayOfItem), 
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
	 * This method list a particular item details
	 * from xero by using item id.
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
	 * This method lists all items details from xero 
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
		return "item";
	}

}
