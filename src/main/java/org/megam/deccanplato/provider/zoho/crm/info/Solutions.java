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
 *This class populates an XML data to send as input to zoho solution create.
 *it calls from solution business function method, and it takes input from that same method.
 */
@XmlRootElement(name="Solutions")
public class Solutions extends XMLBase{

	private static final String SOLUTION_TITLE="Solution Title";
	private static final String STATUS="Status";
	private static final String QUESTION="Question";
	private static final String ANSWER="Answer";
	
	public  Solutions() {
		super();
		createRow();
	}
		
	public void setSolution_Title(String value){
		super.setValueAtCurrentRow(SOLUTION_TITLE, value);
	}
	public void setQuestion(String value){
		super.setValueAtCurrentRow(QUESTION, value);
	}
	public void setStatus(String value){
		super.setValueAtCurrentRow(STATUS, value);
	}	
	public void setAnswer(String value){
		super.setValueAtCurrentRow(ANSWER, value);
	}
}
