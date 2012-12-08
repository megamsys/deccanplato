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
 *This class populates an XML data to send as input to zoho calls create.
 *it calls from call business function method, and it takes input from that same method.
 */
@XmlRootElement(name="Calls")
public class Calls extends XMLBase{

	private static final String SUBJECT="Subject";
	private static final String CALL_TYPE="Call Type";
	private static final String CALL_FROM_TO="Call From/To";
	private static final String CONTACT_NAME="Contact Name";
	private static final String CALL_START_TIME="Call Start Time";
	private static final String CALL_DURATION="Call Duration";
	
	public Calls() {
		super();
		createRow();
	}
		
	public void setSubject(String value){
		super.setValueAtCurrentRow(SUBJECT, value);
	}
	public void setCall_From_To(String value){
		super.setValueAtCurrentRow(CALL_FROM_TO, value);
	}
	public void setContact_Name(String value){
		super.setValueAtCurrentRow(CONTACT_NAME, value);
	}	
	public void setCall_Start_Time(String value){
		super.setValueAtCurrentRow(CALL_START_TIME, value);
	}
	public void setCall_Duration(String value){
		super.setValueAtCurrentRow(CALL_DURATION, value);
	}
	public void setCall_Type(String value){
		super.setValueAtCurrentRow(CALL_TYPE, value);
	}
}
