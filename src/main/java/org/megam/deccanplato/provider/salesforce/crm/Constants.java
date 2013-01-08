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
package org.megam.deccanplato.provider.salesforce.crm;

/**
 * @author ram
 * 
 */
public class Constants {

	/**
	 * ===================================
	 * SALESFORCE CRM INPUT DATA
	 * ===================================
	 */
	/**
	 * The constants that are used in Salesforce side. Salesforce input
	 * constants
	 */
	public static final String S_PASSWORD = "password";
	public static final String S_GRANT_TYPE = "grant_type";
	public static final String S_CLIENT_ID = "client_id";
	public static final String S_CLIENT_SECRET = "client_secret";
	public static final String S_USER_NAME = "username";
	public static final String S_AUTHORIZATION = "Authorization";
	public static final String S_OAUTH = "OAuth ";
	/**
	 * Salesforce Common fields
	 */
	public static final String S_NAME="Name";
	public static final String S_SUBJECT="Subject";
	public static final String S_CONTACTID="ContactId";
	public static final String S_ACCOUNTID="AccountId";
	public static final String S_STATUS="status";
	public static final String S_COMPANY="Company";
	public static final String S_DESCRIPTION="Description";
	/**
	 * Salesforce side user field data
	 */
	public static final String S_USERNAME="Username";
	public static final String S_FIRSTNAME="FirstName";
	public static final String S_EMAIL="Email";
	public static final String S_ALIAS="Alias";
	public static final String S_PROFILEID="ProfileId";
	public static final String S_LASTNAME="LastName";
	public static final String S_TIMEZONESIDKEY="TimeZoneSidKey";
	public static final String S_LOCALESIDKEY="LocaleSidKey";
	public static final String S_EMAILENCODINGKEY="EmailEncodingKey";
	public static final String S_LANGUAGELOCALEYKEY="LanguageLocaleKey";
	/**
	 * Salesforce Account fields
	 */
	
	/**
	 * Salesforce Case fields
	 */
	
	/**
	 * Salesforce Contact Fields
	 */
	
	/**
	 * Salesforce Contact fields
	 */
	public static final String S_CONTRACTTERM="ContractTerm";
	public static final String S_BILLINGCITY="BillingCity";
	/**
	 * Salesforce Event fields
	 */
	public static final String S_STARTDATETIME="StartDateTime";
	public static final String S_ENDDATETIME="EndDateTime";
	/**
	 * Salesforce Lead fields
	 */
	public static final String S_STAGENAME="StageName";
	public static final String S_CLOSEDATE="CloseDate";
	/**
	 * Salesforce Partner fields
	 */
	public static final String S_ACCOUNTFROMIDE="AccountFromId";
	public static final String S_ACCOUNTTOID="AccountToId";
	public static final String S_ISPRIMARY="IsPrimary";
	public static final String S_OPPORTUNITYID="OpportunityId";
	public static final String S_ROLE="Role";
	/**
	 * Salesforce PriceBook fields
	 */
	public static final String S_ISACTIVE="IsActive";
	/**
	 * Salesforce Solution fields
	 */
	public static final String S_SOLUTIONNAME="SolutionName";
	/**
	 * Salesforce Task fields
	 */
	public static final String S_PRIORITY="Priority";
	/**
	 * 
	 * =====================================
	 * JSON INPUT DATA
	 * =====================================
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
	/**
	 * PriceBook
	 */
	public static final String ISACTIVE="isactive";
	/**
	 * Partner
	 */
	public static final String ACCOUNT_FROM_ID="account_from_id";
	public static final String ACCOUNT_TO_ID="account_to_id";
	public static final String ISPRIMARY="isprimary";
	public static final String OPPORTUNITY_ID="opportunity_id";
	public static final String ROLE="role";
}
