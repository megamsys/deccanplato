package org.megam.deccanplato.provider.zoho.crm.info;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;



public class XMLFieldRows {

	
	private int no = 0;
	
	private List<SingleFieldEntryType> fields = new ArrayList<SingleFieldEntryType>();
	
	public XMLFieldRows() {
	}

	public XMLFieldRows(int tempNo) {
		this.no = tempNo;
	}

	public void add(String key, String val) {
		fields.add(new SingleFieldEntryType(key,val));
	}	
	
	@XmlElement (name ="FL")
	public List<SingleFieldEntryType> getMyFieldEntryType() {
		return fields;
	}
	
	@XmlAttribute(name = "no")
	public int getRowNumber() {
		return no;
	}
	


}
