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
 *This class populates an XML data to send as input to zoho event create.
 *it calls from event business function method, and it takes input from that same method.
 */@XmlRootElement(name="Events")
public class Events extends XMLBase{

	private static final String SUBJECT="Subject";
	private static final String START_DATE_TIME="Start DateTime";
	private static final String END_DATE_TIME="End DateTime";
	private static final String VENUE="Venue";
	
	public  Events() {
		super();
		createRow();
	}
		
	public void setSubject(String value){
		super.setValueAtCurrentRow(SUBJECT, value);
	}
	public void setStart_DateTime(String value){
		super.setValueAtCurrentRow(START_DATE_TIME, value);
	}
	public void setEnd_DateTime(String value){
		super.setValueAtCurrentRow(END_DATE_TIME, value);
	}	
	public void setVenue(String value){
		super.setValueAtCurrentRow(VENUE, value);
	}
}
