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
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;

import org.megam.core.util.XMLUtils;

/**
 * @author pandiyaraja
 *
 *This class sets property for expencesCategory and
 *it accessed from ExpenseCategoryimpl. it parses
 *given input to xml data
 */
@XmlRootElement(name="ExpenseCategory")
public class ExpenseCategory {

	@XmlElement(name="CategoryID")
	private String categoryID;
	@XmlElement(name="CategoryName")
	private String categoryName;
	@XmlElement(name="Description")
	private String description;
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String toXMLString() throws JAXBException {
		return XMLUtils.marshalAsString(getClass(), this);
	}	
}
