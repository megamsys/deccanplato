package org.megam.deccanplato.provider.crm.info;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class MyFieldEntryType {


	private String attr;
	private String value;
	
	public MyFieldEntryType () {
		
	}
	
	public MyFieldEntryType (String attr, String val) {
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
