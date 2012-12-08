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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pandiyaraja
 *
 *this class sets the custom field values to use in 
 *customerimpl business class. this class belongs to 
 *customer class.
 */

public class CustomFields {

	@XmlElement(name="CustomFieldLabel1")
	private String field1_name;
	@XmlElement(name="CustomFieldValue1")
	private String field1_value;
	@XmlElement(name="CustomFieldLabel2")
	private String field2_name;
	@XmlElement(name="CustomFieldValue2")
	private String field2_value;
	@XmlElement(name="CustomFieldLabel3")
	private String field3_name;
	@XmlElement(name="CustomFieldValue3")
	private String field3_value;
	
	public void setField1_name(String field1_name) {
		this.field1_name = field1_name;
	}
	public void setField1_value(String field1_value) {
		this.field1_value = field1_value;
	}
	public void setField2_name(String field2_name) {
		this.field2_name = field2_name;
	}
	public void setField2_value(String field2_value) {
		this.field2_value = field2_value;
	}
	public void setField3_name(String field3_name) {
		this.field3_name = field3_name;
	}
	public void setField3_value(String field3_value) {
		this.field3_value = field3_value;
	}
	
}
