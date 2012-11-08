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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * 
 * @author pandiyaraja
 *
 *this class used to set coloum value in the xml population data
 */
public class SingleFieldEntryType {


	private String attr;
	private String value;
	
	public SingleFieldEntryType () {
		
	}
	
	public SingleFieldEntryType (String attr, String val) {
		this.attr = attr;
		value = val;
	}
	
	@XmlAttribute(name = "val")
	public String getAttribute() {
		return attr;
	}
	@XmlValue
	public String getValue() {
		return value;
	}
	
	


}
