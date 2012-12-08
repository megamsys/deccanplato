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

import javax.xml.bind.annotation.XmlElement;

/**
 * @author pandiyaraja
 *
 *this class accesses from estimate class
 *this class has a super class to set the delete as true
 *during Estimate update business method.
 *this class sets the estimate item property
 */
public class EstimateItem extends InvoiceUpdate{

	@XmlElement(name="ProductID")
	private String ProductID;
	
	public EstimateItem() {
		super();
	}
	public EstimateItem(boolean bool) {
          if(bool) {
        	  InvoiceUpdate contupd=new InvoiceUpdate(bool);        	  
          }
          else{
        	  InvoiceUpdate contupd=new InvoiceUpdate(bool);
          }
	}

	public void setProductID(String productID) {
		ProductID = productID;
	}
	
}
