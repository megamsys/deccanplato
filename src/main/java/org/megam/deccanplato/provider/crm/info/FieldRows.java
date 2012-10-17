package org.megam.deccanplato.provider.crm.info;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;


public class FieldRows {

	
	private int no = 0;
	
	private List<MyFieldEntryType> fields = new ArrayList<MyFieldEntryType>();
	
	public FieldRows() {
	}

	public FieldRows(int tempNo) {
		this.no = tempNo;
	}

	public void add(String key, String val) {
		fields.add(new MyFieldEntryType(key,val));
	}	
	
	@XmlElement (name ="FL")
	public List<MyFieldEntryType> getMyFieldEntryType() {
		return fields;
	}
	
	@XmlAttribute(name = "no")
	public int getRowNumber() {
		return no;
	}
	


}
