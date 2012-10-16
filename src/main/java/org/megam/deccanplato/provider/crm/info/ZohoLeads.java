package org.megam.deccanplato.provider.crm.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement("Lead")
public class ZohoLeads {

	public FieldRows rows = new FieldRows();

	private static String OWNER_ID = "OWNERID...";

	public ZohoLeads() {

	}

	public void setOwnerId(String value) {
		rows.add(OWNER_ID, value);
	}

}