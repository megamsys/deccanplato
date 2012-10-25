package org.megam.deccanplato.provider.zoho.crm.info;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class SingleFieldEntryType {


	private String attr;
	private String value;
	
	public SingleFieldEntryType () {
		
	}
	
	public SingleFieldEntryType (String attr, String val) {
		this.attr = attr;
		value = val;
	}
	
	@XmlAttribute(name = "val")
	public String getAttribute() {
		return attr;
	}
	@XmlValue
	public String getValue() {
		return value;
	}
	
	


}
