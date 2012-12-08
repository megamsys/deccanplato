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
package org.megam.deccanplato.provider.zoho.crm.info;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author pandiyaraja
 *
 *This class populates an XML data to send as input to zoho contact create.
 *it calls from contact business function method, and it takes input from that same method.
 */
@XmlRootElement (name = "Contacts")
public class Contacts extends XMLBase{
	public static final String LAST_NAME="Last Name";
	public static final String EMAIL="Email";
	public static final String LEAD_SOURCE="Lead Source";
	//public static final String SIC_CODE="SIC Code";
	public static final String PHONE="Phone";
	public static final String FAX="Fax";
	//public static final String SMOWNERID="SMOWNERID";
	//public static final String ACCOUNT_OWNER="Account Owner";
	

	
	public  Contacts() {
		super();
		createRow();
	}
		
	public void setLast_Name(String value){
		super.setValueAtCurrentRow(LAST_NAME, value);
	}
	public void setEmail(String value){
		super.setValueAtCurrentRow(EMAIL, value);
	}
	public void setLead_Source(String value){
		super.setValueAtCurrentRow(LEAD_SOURCE, value);
	}
	/*public void setSIC_Code(String value){
		super.setValueAtCurrentRow(SIC_CODE, value);
	}*/
	public void setPhone(String value){
		super.setValueAtCurrentRow(PHONE, value);
	}
	public void setFax(String value){
		super.setValueAtCurrentRow(FAX, value);
	}
	/*public void setSmowner(String value){
		super.setValueAtCurrentRow(SMOWNERID, value);
	}*/
	/*public void setAccount_owner(String value){
		super.setValueAtCurrentRow(ACCOUNT_OWNER, value);
	}*/

}
