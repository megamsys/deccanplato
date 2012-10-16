package org.megam.deccanplato.provider.crm.info;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement("fl")
public class MyFieldEntryType {

	@JacksonXmlProperty(isAttribute = true)
	public String val;

	@JacksonXmlText
	public String value;

}
