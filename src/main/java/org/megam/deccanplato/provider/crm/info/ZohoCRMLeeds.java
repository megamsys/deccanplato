package org.megam.deccanplato.provider.crm.info;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "Leeds")
public class ZohoCRMLeeds extends ZohoCRMXMLBase {
	
	private static  final String OWNER_ID = "OWNER_ID";
	
	public ZohoCRMLeeds() {
		super();
		createRow();		
	}
	
	public void setOwnerId(String val) {
		//Add the key,value to the currentRow .
	}

}