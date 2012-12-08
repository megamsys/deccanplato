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
package org.megam.deccanplato.provider.zoho.invoice;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author pandiyaraja
 *
 *constants are used in zoho invoice business implementation method
 */
public class Constants {
	
	/**
	 * The constants that are used in ZOHO side.
	 * ZOHO input constants
	 */
	  public static final String ID="id";
	  public static final String OAUTH_TOKEN="authtoken";
	  public static final String APIKEY="apikey";
	  public static final String ZOHO_SCOPE="scope";
	  public static final String ZOHO_XMLSTRING="XMLString";
	  public static final String ZOHO_TYPE="type";
	  public static final String ZOHO_STRING="";
	  
	  /**
	   * common
	   * Json Input constants
	   */
	  public static final String AUTHTOKEN="OAuth_token";
	  public static final String SCOPE="crmapi";
	  /**
	   * Customers
	   * Json Input constants
	   */
	  public static final String NAME="name";
	  public static final String PAYMENT_DUE="payments_due";
	  public static final String CURRENCY_CODE="currency_code";
	  public static final String BILLING_ADDESS="billing_address";
	  public static final String BILLING_CITY="billing_city";
	  public static final String BILLING_ZIP="billing_zip";
	  public static final String BILLING_COUNTRY="billing_country";
	  public static final String BILLING_FAX="billing_fax";
	  public static final String SHIPPING_ADDRESS="shipping_address";
	  public static final String SHIPPING_CITY="shipping_city";
	  public static final String SHIPPING_STATE="shipping_state";
	  public static final String SHIPPING_ZIP="shipping_zip";
	  public static final String SHIPPING_COUNTRY="shipping_country";
	  public static final String SHIPPING_FAX="shipping_fax";
	  public static final String EMAIL="email";
	  public static final String FIRST_NAME="first_name";
	  public static final String LAST_NAME="last_name";
	  public static final String MOBILE="mobile";
	  public static final String PHONE="phone";
	  public static final String SALUTATION="salutation";
	  public static final String LABEL1="label1";
	  public static final String LABEL2="label2";
	  public static final String LABEL3="label3";
	  public static final String VALUE1="value1";
	  public static final String VALUE2="value2";
	  public static final String VALUE3="value3";
	  public static final String NOTES="notes";
	  public static final String CUSTOMERID="customer_id";
	  public static final String CONTACT_ID="contact_id";
	  public static final String DELETE_CONTACT="delete_contact";
	  /**
	   * Items
	   * Json Input constants
	   */
	 public static final String ITEM_NAME="item_name";
	 public static final String DESCRIPTION="description";
	 public static final String RATE="rate";
	 public static final String TAX_NAME_1="tax_name1";
	/**
	 * Estimate
	 * Json Input constants
	 */
	public static final String ESTIMATE_DATE="estimate_date";
	public static final String REFERENCE_NO="reference_no";
	public static final String EXCHANGE_RATE="exchange_rate";
	public static final String CUSTOM_BODY="custom_body";
	public static final String CUSTOM_SUBJECT="custom_subject";
	public static final String PRODUCT_ID="product_id";
	public static final String TERMS="terms";
	public static final String ESTIMATE_EMAIL="estimate_email";
	public static final String TO_EMAIL="to_email";
	public static final String CC_EMAIL="cc_email";	
	public static final String DELETE_ESTIMATE="delete_estimate";
	/**
	 * Invoice
	 * Json Input constants
	 */
	public static final String INVOICE_DATE="invoice_date";
	public static final String PONUMPER="po_number";
	public static final String AUTHORIZE_NET="authorize_net";
	public static final String DELETE_INVOICE="delete_invoice";
	/**
	 * ExpenseCategory
	 * Json Input constants
	 */	
	public static final String CATEGORY_NAME="category_name";	
	/**
	 * Expense
	 * Json Input constants
	 */
	public static final String EXPENSE_DATE="expense_date";
	public static final String AMOUNT="amount";
	public static final String INCLUSIVE_TAX="inclusive_tax";
	public static final String BILLABLE="billable";
	public static final String REFERENCE="refrence";
	/**
	 * Payment
	 * Json Input constants
	 */
	public static final String INVOICE_ID="invoice_id";
	public static final String MODE="mode";
	public static final String DATE="date";
}
