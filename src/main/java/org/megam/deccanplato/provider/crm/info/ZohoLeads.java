package org.megam.deccanplato.provider.crm.info;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "Leads")
public class ZohoLeads {

	private FieldRows rows;

	private static String OWNER_ID = "OWNERID";

	public ZohoLeads() {
		rows =  new FieldRows(1);

	}

	public void setOwnerId(String value) {
		rows.add(OWNER_ID, value);
	}
	
	@XmlElement(name ="row")
	public FieldRows getRows() {
		return rows;
	}

}