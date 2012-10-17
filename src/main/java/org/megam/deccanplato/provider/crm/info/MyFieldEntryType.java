package org.megam.deccanplato.provider.crm.info;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement(localName ="fl")
public class MyFieldEntryType {

	@JacksonXmlProperty(isAttribute = true)
	public String val;

	@JacksonXmlText
	public String value;

	public MyFieldEntryType(String val, String value){
		this.val=val;
		this.value=value;
	}

}
