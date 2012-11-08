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

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author pandiyaraja
 *
 *This class populates an XML data to send as input to zoho account create.
 *it calls from account business function method, and it takes in put from that same method.
 */
@XmlRootElement (name = "Accounts")
public class Accounts extends XMLBase{
	
	public static final String ACCOUNT_NAME="Account Name";
	public static final String EMPLOYEES="Employees";
	public static final String ANNUAL_REVENUE="Annual Revenue";
	//public static final String SIC_CODE="SIC Code";
	public static final String PHONE="Phone";
	public static final String FAX="Fax";
	//public static final String SMOWNERID="SMOWNERID";
	//public static final String ACCOUNT_OWNER="Account Owner";
	

	
	public  Accounts() {
		super();
		createRow();
	}
		
	public void setAccount_Name(String value){
		super.setValueAtCurrentRow(ACCOUNT_NAME, value);
	}
	public void setEmployees(String value){
		super.setValueAtCurrentRow(EMPLOYEES, value);
	}
	public void setAnual_Revenue(String value){
		super.setValueAtCurrentRow(ANNUAL_REVENUE, value);
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
