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
 *This class accessed from expenseimpl business class
 *this class sets the property for expense and it parses
 *given input to xml data. 
 */
@XmlRootElement(name="Expense")
public class Expense {

	@XmlElement(name="ExpenseID")
	private String expenseID;
	@XmlElement(name="CategoryName")
	private String categoryName;
	@XmlElement(name="ExpenseDate")
	private String expenseDate;
	@XmlElement(name="ExpenseAmount")
	private String expenseAmount;
	@XmlElement(name="ExpenseDescription")
	private String expenseDescription;
	@XmlElement(name="Tax1Name")
	private String tax1Name;
	@XmlElement(name="IsInclusiveTax")
	private String isInclusiveTax;
	@XmlElement(name="IsBillable")
	private String isBillable;
	@XmlElement(name="Reference")
	private String reference;
	@XmlElement(name="CustomerID")
	private String customerID;
	@XmlElement(name="CurrencyCode")
	private String currencyCode;
	@XmlElement(name="ExchangeRate")
	private String exchangeRate;
	public void setExpenseID(String expenseID) {
		this.expenseID = expenseID;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}
	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
	public void setTax1Name(String tax1Name) {
		this.tax1Name = tax1Name;
	}
	public void setIsInclusiveTax(String isInclusiveTax) {
		this.isInclusiveTax = isInclusiveTax;
	}
	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String toXMLString() throws JAXBException {
		return XMLUtils.marshalAsString(getClass(), this);
	}
}
