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
 *This class populates an XML data to send as input to zoho campaign create.
 *it calls from campaign business function method, and it takes input from that same method.
 */
@XmlRootElement(name="Campaigns")
public class Campaigns extends XMLBase{

	public static final String CAMPAIGN_NAME="Campaign Name";
	public static final String STATUS="status";
	public static final String START_DATE="Start Date";
	public static final String END_DATE="End Date";
	public static final String EXPECTED_REVENUE="Expected Revenue";
	public static final String BUDGETED_COST="Budgeted Cost";
	public static final String ACTUAL_COST="Actual Cost";
	
	

	
	public  Campaigns() {
		super();
		createRow();
	}
		
	public void setCampaign_Name(String value){
		super.setValueAtCurrentRow(CAMPAIGN_NAME, value);
	}
	public void setStart_Date(String value){
		super.setValueAtCurrentRow(START_DATE, value);
	}
	public void setStatus(String value){
		super.setValueAtCurrentRow(STATUS, value);
	}
	/*public void setSIC_Code(String value){
		super.setValueAtCurrentRow(SIC_CODE, value);
	}*/
	public void setEnd_Date(String value){
		super.setValueAtCurrentRow(END_DATE, value);
	}
	public void setExpected_Revenue(String value){
		super.setValueAtCurrentRow(EXPECTED_REVENUE, value);
	}
	public void setBudgeted_Cost(String value){
		super.setValueAtCurrentRow(BUDGETED_COST, value);
	}
	public void setActual_Cost(String value){
		super.setValueAtCurrentRow(ACTUAL_COST, value);
	}
}
