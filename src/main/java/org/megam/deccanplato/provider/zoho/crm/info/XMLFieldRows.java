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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
/**
 * 
 * @author pandiyaraja
 *
 *This class sets field values in the row
 */


public class XMLFieldRows {

	
	private int no = 0;
	
	private List<SingleFieldEntryType> fields = new ArrayList<SingleFieldEntryType>();
	
	public XMLFieldRows() {
	}

	public XMLFieldRows(int tempNo) {
		this.no = tempNo;
	}

	public void add(String key, String val) {
		fields.add(new SingleFieldEntryType(key,val));
	}	
	
	@XmlElement (name ="FL")
	public List<SingleFieldEntryType> getMyFieldEntryType() {
		return fields;
	}
	
	@XmlAttribute(name = "no")
	public int getRowNumber() {
		return no;
	}
	


}
