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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import net.oauth.OAuthMessage;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.rossjourdain.util.xero.ArrayOfAccount;
import com.rossjourdain.util.xero.ResponseType;
import com.rossjourdain.util.xero.XeroClientException;
import com.rossjourdain.util.xero.XeroClientUnexpectedException;
import com.rossjourdain.util.xero.XeroPublicClient;
import com.rossjourdain.util.xero.XeroXmlManager;

/**
 * @author pandiyaraja
 *
 */
public class AccountImpl implements BusinessActivity {

	private Map<String, String> args = new HashMap<String, String>();
	private BusinessActivityInfo bizInfo;
	private XeroPublicClient client;
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
		Map<String, String> outMap=new HashMap<String, String>();
		switch(bizInfo.getActivityFunction()) {
		case LIST:
			outMap=list(outMap);
			break;
		case VIEW:
			outMap=view(outMap);
			break;
		}
		return null;
	}

	/**
	 * This method returns a particular account details by
	 * account id. It converts response data to xml data by using 
	 * XeroXmlManager class.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> view(Map<String, String> outMap) {
		/*String accountList = null;
		ArrayOfAccount arrayOfAccount = null;
		ResponseType responseType = null;
		StringTokenizer stok=new StringTokenizer(args.get(BIZ_FUNCTION), "#");
		try {
        	client=new XeroPublicClient(args);
        	OAuthMessage response=client.getXero(args.get(ID), stok.nextToken());
			responseType = XeroXmlManager.fromXml(response.getBodyAsStream());	
			arrayOfAccount = responseType.getAccounts();
			accountList=XeroXmlManager.accountsToXml(arrayOfAccount);
			System.out.println(XeroXmlManager.accountsToXml(arrayOfAccount));
		} catch (XeroClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XeroClientUnexpectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, accountList);
		return outMap;
		*/
		return null;
	}

	/**
	 *  This method returns a list of account details by
	 * account id. It converts response data to xml data by using 
	 * XeroXmlManager class.
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		/*String invoiceList = null;
		ResponseType responseType = null;
		StringTokenizer stok=new StringTokenizer(args.get(BIZ_FUNCTION), "#");
		ArrayOfAccount arrayOfAccount = null;
        try {
        	XeroPublicClient client=new XeroPublicClient(args);
			OAuthMessage response=client.getXeros(stok.nextToken());
			responseType = XeroXmlManager.fromXml(response.getBodyAsStream());	
			arrayOfAccount = responseType.getAccounts();
			invoiceList=XeroXmlManager.accountsToXml(arrayOfAccount);
			System.out.println(XeroXmlManager.accountsToXml(arrayOfAccount));
		} catch (XeroClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XeroClientUnexpectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outMap.put(OUTPUT, invoiceList);
		return outMap;
		*/
		return null;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "account";
	}

}
