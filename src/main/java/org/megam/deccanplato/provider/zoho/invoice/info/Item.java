/**
 * “Copyright 2012 Megam Systems”
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.megam.deccanplato.provider.zoho.invoice.info;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.megam.core.util.XMLUtils;

/**
 * @author pandiyaraja
 * 
 *this class sets the properties for invoice item
 *it access from the business class itemimpl 
 */
@XmlRootElement(name="Item")
public class Item {
    @XmlElement(name="ItemID")
	private String ItemID;	
    @XmlElement(name="Name")
	private String Name;
    @XmlElement(name="Description")
	private String Description;
    @XmlElement(name="Rate")
	private String Rate;
    @XmlElement(name="Tax1Name")
	private String Tax1Name;
    
	
	public void setItemID(String itemID) {
		ItemID = itemID;
	}	
	public void setName(String name) {
		Name = name;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public void setRate(String rate) {
		Rate = rate;
	}
	public void setTax1Name(String tax1Name) {
		Tax1Name = tax1Name;
	}
	public String toXMLString() throws JAXBException {
		return XMLUtils.marshalAsString(getClass(), this);
	}	

}
