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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author pandiyaraja
 * 
 * this class sets the information about contact and it belongs to
 * the class Customer business, this class has a super class InvoiceUpdate
 * contact class acess the data from super to set delete as true at update
 * business method
 */
@XmlRootElement(name = "contact")
public class Contact extends InvoiceUpdate{

	@XmlElement(name = "Salutation")
	private String salutation;
	@XmlElement(name = "FirstName")
	private String firstName;
	@XmlElement(name = "LastName")
	private String lastName;
	@XmlElement(name = "EMail")
	private String email;
	@XmlElement(name = "Phone")
	private String phone;
	@XmlElement(name = "Mobile")
	private String mobile;
	@XmlElement(name = "ContactID")
	private String contactId;
	private boolean contbool=true;

	public Contact() {
		super();
	}
	public Contact(boolean bool) {
          if(bool) {
        	  InvoiceUpdate contupd=new InvoiceUpdate(bool);        	  
          }
          else{
        	  InvoiceUpdate contupd=new InvoiceUpdate(bool);
          }
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
