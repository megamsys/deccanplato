package org.megam.deccanplato.provider.crm.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.RowSet;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement(localName = "Lead")
public class ZohoLeads {

	public FieldRows rows;

	private static String OWNER_ID = "SMOWNERID";
	private static String LEAD_OWNER = "Lead Owner";
	private static String company = "Company";
	private static String First_Name = "First Name";
	private static String Last_Name = "Last Name";
	private static String designation = "Designation";
	private static String Email = "Email";
	private static String Phone = "Phone";
	private static String Fax = "Fax";
	private static String Mobile = "Mobile";
	private static String Website = "Website";
	private static String Lead_Source = "Lead Source";
	private static String Lead_Status = "Lead Status";
	
	public ZohoLeads() {
		 rows=new FieldRows(1);
	}

	public void setOwnerId(String value) {
		rows.add(OWNER_ID, value);
	}

	public void setLEAD_OWNER(String value) {
		rows.add(LEAD_OWNER, value);
	}
	
}