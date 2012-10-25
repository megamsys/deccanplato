package org.megam.deccanplato.provider.zoho.crm.info;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.megam.core.util.XMLUtils;

public class XMLBase {

	private List<XMLFieldRows> rows = new ArrayList<XMLFieldRows>();
	
	@XmlTransient
	private int counter = 1;
	
	@XmlTransient
	private XMLFieldRows currentRow;

	XMLBase() {
	}

	protected void createRow() {
		XMLFieldRows row = new XMLFieldRows(counter++);
		currentRow = row;
		rows.add(row);
	}

	private XMLFieldRows getCurrentRow() {
		return currentRow;
	}
	
	@XmlElement(name = "row")
	public List<XMLFieldRows> getRowsList() {
		return rows;
	}
	
	public void setValueAtCurrentRow(String key, String val) {
		getCurrentRow().add(key, val);
	}
	
	public String toXMLString() throws JAXBException {
		return XMLUtils.marshalAsString(getClass(), this);
	}
	
	public <T extends Object> T toObject(String input) throws JAXBException {
		return XMLUtils.unmarshalFromString(getClass(), input);
	}

}