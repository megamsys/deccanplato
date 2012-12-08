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

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.megam.core.util.XMLUtils;

/**
 * @author pandiyaraja
 *
 *payment class sets properties for payment 
 *this class defines the payment mode
 *it access from paymentimpl business class 
 */
@XmlRootElement(name="Payment")
public class Payment {

	@XmlElement(name="PaymentID")
	private String PaymentID;
	@XmlElement(name="InvoiceID")
	private String InvoiceID;
	@XmlElement(name="Mode")
	private String Mode;
	@XmlElement(name="Description")
	private String Description;
	@XmlElement(name="Date")
	private String Date;
	@XmlElement(name="ExchangeRate")
	private String ExchangeRate;
	@XmlElement(name="Amount")
	private String Amount;
	
	public void setPaymentID(String paymentID) {
		PaymentID = paymentID;
	}
	public void setInvoiceID(String invoiceID) {
		InvoiceID = invoiceID;
	}
	public void setMode(String mode) {
		Mode = mode;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public void setDate(String date) {
		Date = date;
	}
	public void setExchangeRate(String exchangeRate) {
		ExchangeRate = exchangeRate;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String toXMLString() throws JAXBException {
		return XMLUtils.marshalAsString(getClass(), this);
	}
}
