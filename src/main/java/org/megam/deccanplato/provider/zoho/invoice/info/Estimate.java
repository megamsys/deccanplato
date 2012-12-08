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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.megam.core.util.XMLUtils;

/**
 * @author pandiyaraja
 *
 *this class sets the estimate details to access in estimateimpl 
 *business class. this class this class has estimateitem class
 *type list and Comment class type list to store the property of 
 *estimateitem and comment class properties and access that properties
 *this class parses given input value to xml data. 
 */
@XmlRootElement(name="Estimate")
public class Estimate {

	@XmlElement(name="ESTIMATEID")
	private String estimateId;
	@XmlElement(name="CustomerID")
	private String CustomerID;
	@XmlElement(name="EstimateDate")
	private String EstimateDate;
	@XmlElement(name="ReferenceNumber")
	private String ReferenceNumber;
	@XmlElement(name="ExchangeRate")
	private String ExchangeRate;
	@XmlElement(name="Custom.Body")
	private String Custom_Body;
	@XmlElement(name="Custom.Subject")
	private String Custom_Subject;
	private String Notes;
	private String Terms;
	@XmlElementWrapper(name="EstimateItems")
	private List<EstimateItem> EstimateItem=new ArrayList<EstimateItem>();
	@XmlElementWrapper(name="Comments")
	private List<Comment> Comment=new ArrayList<Comment>();
	
	
	public void setEstimateId(String estimateId) {
		this.estimateId = estimateId;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public void setEstimateDate(String estimateDate) {
		EstimateDate = estimateDate;
	}
	public void setReferenceNumber(String referenceNumber) {
		ReferenceNumber = referenceNumber;
	}
	public void setExchangeRate(String exchangeRate) {
		ExchangeRate = exchangeRate;
	}
	public void setCustom_Body(String custom_Body) {
		Custom_Body = custom_Body;
	}
	public void setCustom_Subject(String custom_Subject) {
		Custom_Subject = custom_Subject;
	}
	@XmlElement(name="Notes")
	public void setNotes(String notes) {
		Notes = notes;
	}
	@XmlElement(name="Terms")
	public void setTerms(String terms) {
		Terms = terms;
	}
	public List<EstimateItem> getEstimateItem() {
		return EstimateItem;
	}
	public List<Comment> getComment() {
		return Comment;
	}	
	public String getNotes() {
		return Notes;
	}
	public String getTerms() {
		return Terms;
	}
	public String toXMLString() throws JAXBException {
		return XMLUtils.marshalAsString(getClass(), this);
	}	
}
