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
 *payment gateway sets online net banking
 *this class belongs to the invoice class
 */

public class PaymentGateway {

	@XmlElement(name="Authorize.Net")
	private String authorize_Net;

	public void setAuthorize_Net(String authorize_Net) {
		this.authorize_Net = authorize_Net;
	}
	
}
