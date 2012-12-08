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
 *Invoice class sets property for invoice business class
 *this class access from invoiceimpl business class.
 *it has paymentgateway class type list and invoiceitem
 *class type listto store properties from that classes and
 *access that properties. this class parses the given input
 *to xml data.
 */
@XmlRootElement(name="Invoice")
public class Invoice {

	@XmlElement(name="InvoiceID")
	private String invoiceID;
	@XmlElement(name="CustomerID")
	private String CustomerID;
	@XmlElement(name="InvoiceDate")
	private String InvoiceDate;
	@XmlElement(name="PONumber")
	private String PONumber;
	@XmlElement(name="ExchangeRate")
	private String ExchangeRate;
	@XmlElement(name="Custom.Body")
	private String Custom_Body;
	@XmlElement(name="Custom.Subject")
	private String Custom_Subject;
	private String Notes;
	private String Terms;
	
	List<PaymentGateway> PaymentGateways=new ArrayList<PaymentGateway>();
	
	@XmlElementWrapper(name="InvoiceItems")
	List<InvoiceItem> InvoiceItem=new ArrayList<InvoiceItem>();
	
	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public void setInvoiceDate(String invoiceDate) {
		InvoiceDate = invoiceDate;
	}
	public void setPONumber(String pONumber) {
		PONumber = pONumber;
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
	public String getNotes() {
		return Notes;
	}
	public String getTerms() {
		return Terms;
	}
	@XmlElement(name="PaymentGateways")
	public List<PaymentGateway> getPaymentGateways() {
		return PaymentGateways;
	}	
	
	public List<InvoiceItem> getInvoiceItem() {
		return InvoiceItem;
	}
	public String toXMLString() throws JAXBException {
		return XMLUtils.marshalAsString(getClass(), this);
	}
}
