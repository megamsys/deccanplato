package org.megam.deccanplato.provider.crm.info;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "row")
public class FieldRows {

	@JacksonXmlProperty(isAttribute = true)
	public int no = 0;

	
	public List<MyFieldEntryType> FL = new ArrayList<MyFieldEntryType>();

	public void add(String key, String val) {
		FL.add(new MyFieldEntryType(key, val));
	}
    public FieldRows(int i){
    	this.no=i;
    }
}
