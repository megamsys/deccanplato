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
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.megam.core.util.XMLUtils;

/**
 * @author pandiyaraja
 * 
 *         this class sets customer details and this class accessed from
 *         CustomerImpl business class. this class has a contact class type list
 *         and CustomField class type listto store contact class property and
 *         access. this class converts given input to xml data
 */
@XmlRootElement(name = "Customer")
public class Customer {
	@XmlElement(name = "Name")
	private String name;
	@XmlElement(name = "PaymentsDue")
	private String paymentsDue;
	@XmlElement(name = "CurrencyCode")
	private String currencyCode;
	@XmlElement(name = "BillingAddress")
	private String billingAddress;
	@XmlElement(name = "BillingCity")
	private String billingCity;
	@XmlElement(name = "BillingZip")
	private String billingZip;
	@XmlElement(name = "BillingCountry")
	private String billingCountry;
	@XmlElement(name = "BillingFax")
	private String billingFax;
	@XmlElement(name = "ShippingAddress")
	private String shippingAddress;
	@XmlElement(name = "ShippingCity")
	private String shippingCity;
	@XmlElement(name = "ShippingState")
	private String shippingState;
	@XmlElement(name = "ShippingZip")
	private String shippingZip;
	@XmlElement(name = "ShippingCountry")
	private String shippingCountry;
	@XmlElement(name = "ShippingFax")
	private String shippingFax;
	@XmlElement(name = "CustomerID")
	private String customerId;
	private String notes;
	private Contact update;
	boolean update1 = true;

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@XmlElementWrapper(name = "Contacts")
	List<Contact> Contact = new ArrayList<Contact>();

	List<CustomFields> CustomFields = new ArrayList<CustomFields>();

	@XmlElement(name = "Notes")
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPaymentsDue(String paymentsDue) {
		this.paymentsDue = paymentsDue;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public void setBillingZip(String billingZip) {
		this.billingZip = billingZip;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	public void setBillingFax(String billingFax) {
		this.billingFax = billingFax;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public void setShippingZip(String shippingZip) {
		this.shippingZip = shippingZip;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public void setShippingFax(String shippingFax) {
		this.shippingFax = shippingFax;
	}

	public List<Contact> getContact() {
		return Contact;
	}

	@XmlElement(name = "CustomFields")
	public List<CustomFields> getCustomFields() {
		return CustomFields;
	}

	public String toXMLString() throws JAXBException {
		return XMLUtils.marshalAsString(getClass(), this);
	}

}
