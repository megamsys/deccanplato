/* “Copyright 2012 Megam Systems”
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.megam.deccanplato.provider.zoho.crm.info;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.megam.core.util.XMLUtils;
/**
 * 
 * @author pandiyaraja
 *This class sets row value and also sets the value for that row.
 *it converts string to xmlstring
 */
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