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
 *This class populates an XML data to send as input to zoho potential create.
 *it calls from potential business function method, and it takes input from that same method.
 */
@XmlRootElement (name="Potentials")
public class Potentials extends XMLBase{

	public static final String POTENTIAL_NAME="Potential Name";
	public static final String ACCOUNT_NAME="Account Name";
	public static final String CLOSING_DATE="Closing Date";
	//public static final String SIC_CODE="SIC Code";
	public static final String STAGE="Stage";
	public static final String AMOUNT="Amount";
	//public static final String SMOWNERID="SMOWNERID";
	//public static final String ACCOUNT_OWNER="Account Owner";
	

	
	public  Potentials() {
		super();
		createRow();
	}
		
	public void setPotential_Name(String value){
		super.setValueAtCurrentRow(POTENTIAL_NAME, value);
	}
	public void setAccount_Name(String value){
		super.setValueAtCurrentRow(ACCOUNT_NAME, value);
	}
	public void setClose_Date(String value){
		super.setValueAtCurrentRow(CLOSING_DATE, value);
	}
	/*public void setSIC_Code(String value){
		super.setValueAtCurrentRow(SIC_CODE, value);
	}*/
	public void setStage(String value){
		super.setValueAtCurrentRow(STAGE, value);
	}
	public void setAmount(String value){
		super.setValueAtCurrentRow(AMOUNT, value);
	}
	/*public void setSmowner(String value){
		super.setValueAtCurrentRow(SMOWNERID, value);
	}*/
	/*public void setAccount_owner(String value){
		super.setValueAtCurrentRow(ACCOUNT_OWNER, value);
	}*/
}
