/**
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
package org.megam.deccanplato.provider.salesforce.chatter;

/**
 * @author ram
 * 
 */
public class Constants {

	/**
	 * ===========================
	 * Salesforce Inputs
	 * ===========================
	 */
	/**
	 * The constants that are used in Salesforce side. Salesforce input
	 * constants
	 */
	public static final String S_PASSWORD = "password";
	public static final String S_GRANT_TYPE = "grant_type";
	public static final String S_CLIENT_ID = "client_id";
	public static final String S_CLIENT_SECRET = "client_secret";
	public static final String S_USERNAME = "username";
	public static final String S_AUTHORIZATION = "Authorization";
	public static final String S_OAUTH = "OAuth ";
	/**
	 * Salesforce Feed fields
	 */
	public static final String S_TEXT="text";
	
    /**
     * ===================================
     * Salesforce Chatter Json inputs
     * ===================================
     */
	/**
	 * The constants that are needed for common function. Json Input constants
	 */
	public static final String CLIENT_ID = "consumer_key";
	public static final String CLIENT_SECRET = "consumer_secret";
	public static final String ACCESS_USER_NAME = "access_username";
	public static final String ACCESS_PASSWORD = "access_password";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String INSTANCE_URL = "instance_url";

	/**
	 * The constants that are needed for User business function. Json Input
	 * constants
	 */

	public static final String USERNAME = "user_name";
	public static final String FIRSTNAME = "first_name";
	public static final String EMAIL = "email";
	public static final String ALIAS = "alias";
	public static final String PROFILEID = "profile";
	public static final String LASTNAME = "last_name";
	public static final String TIMEZONESIDKEY = "time_zone";
	public static final String LOCALESIDKEY = "locale";
	public static final String EMAILENCODINGKEY = "charset_encoding";
	public static final String LANGUAGELOCALEKEY = "language";

	/**
	 * The constants that are needed for Account business function. Json Input
	 * constants
	 */
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String TYPE="type";
	public static final String TEXT="text";

	/**
	 * The constants that are needed for Leads business function. Json Input
	 * constants
	 */
	public static final String COMPANY = "company";
	/**
	 * Case Json Input constants
	 */
	public static final String SUBJECT = "subject";
	public static final String CONTACT_ID = "contact_id";
	public static final String ACCOUNT_ID = "account_id";
	/**
	 * Contract Json Input constants
	 */
	public static final String CONTRACT_TERM = "contract_term";
	public static final String BILLING_CITY = "billing_city";
	/**
	 * Event Json Input constants
	 */
	public static final String START_DATE = "start_date";
	public static final String END_DATE = "end_date";
	/**
	 * Opportunity Json Input constants
	 */
	public static final String STAGE_NAME = "stage_name";
	public static final String CLOSE_DATE = "close_date";
	/**
	 * Product Json Input constants
	 */
	public static final String PRODUCT_CODE = "product_code";
	public static final String DESCRIPTION = "description";
	/**
	 * Solution Json Input constants
	 */
	public static final String SOLUTION_NAME = "solution_name";
	public static final String STATUS = "status";
	/**
	 * Task Json Input constants
	 */
	public static final String PRIORITY = "priority";
}
