package org.megam.deccanplato.provider.crm.info;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.megam.core.util.XMLUtils;

public class ZohoCRMXMLBase {

	private List<FieldRows> rows = new ArrayList<FieldRows>();
	
	@XmlTransient
	private int counter = 1;
	
	@XmlTransient
	private FieldRows currentRow;

	ZohoCRMXMLBase() {
	}

	protected void createRow() {
		FieldRows row = new FieldRows(counter++);
		currentRow = row;
		rows.add(row);
	}

	private FieldRows getCurrentRow() {
		return currentRow;
	}
	
	@XmlElement(name = "row")
	public List<FieldRows> getRowsList() {
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