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
package org.megam.deccanplato.provider.zoho.crm;

/**
 * @author pandiyaraja
 *
 */
public class Constants {
	
	/**
	 * The constants that are used in ZOHO side.
	 * ZOHO input constants
	 */
	  public static final String ID="id";
	  public static final String OAUTH_TOKEN="authtoken";
	  public static final String ZOHO_SCOPE="scope";
	  public static final String ZOHO_XMLDATA="xmlData";
	  public static final String ZOHO_TYPE="type";
	  public static final String INSERT_RECORDS="insertRecords?";
	  public static final String UPDATE_RECORDS="updateRecords?";
	  public static final String GET_RECORDS="getRecords";
	  public static final String DELETE_RECORDS="deleteRecords";
    	
	/**
	 * The constants that are needed for User business function.
	 * Json Input constants
	 */
	public static final String TYPE="AllUsers";
	
	/**
	 * The constants that are needed for Account business function.
	 * Json Input constants
	 */
	  public static final String ACCOUNTNAME="account_name";
	  public static final String ANNUALREVENUE="annual_revenue";
	  public static final String EMPLOYEES="employees";
	  public static final String FAX="fax";
	  public static final String PHONE="phone";
	  public static final String ACCOUNTOWNER="account_owner"; 
	 
	
	/**
	 * The constants that are needed for Lead business function.
	 * Json Input constants
	 */
	public static final String AUTHTOKEN="OAuth_token";
	public static final String SCOPE="crmapi";
	public static final String COMPANY="company";
	public static final String FIRSTNAME="first_name";
	public static final String DESIGNATION = "designation";
	public static final String LASTNAME="last_name";
	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile";
	public static final String WEBSITE = "website";
	public static final String LEAD_SOURCE = "lead_source";
	public static final String LEAD_STATUS = "lead_status";
	public static final String INDUSTRY = "industry";
	public static final String ANNUAL_REVENUE = "annusl_revenue";
	public static final String EMAIL_OPT_OUT = "email_out_put";
	public static final String SKYPE_ID = "skype_id";
	public static final String SALUTATION = "salutation";
	public static final String STREET = "street";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String ZIP_CODE = "zip_code";
	public static final String COUNTRY = "country";
	public static final String DESCRIPTION = "description";
	/**
	 * POTENTIALS
	 * Json Input constants
	 */
	public static final String POTENTIAL_NAME="potential_name";
	public static final String ACCOUNT_NAME="account_name_id";
	public static final String CLOSING_DATE="closing_date";
	public static final String STAGE="stage";
	public static final String AMOUNT="amount";
	/**
	 * CAMPAIGNS
	 * Json Input constants
	 */
	public static final String CAMPAIGN_NAME="campaign_name";
	public static final String START_DATE="start_date";
	public static final String END_DATE="end_date";
	public static final String EXPECTED_REVENUE="expected_revenue";
	public static final String BUDGETED_COST="budgeted_cost";
	public static final String ACTUAL_COST="actual_cost";
	public static final String STATUS="status";
	/**
	 * CASES
	 * Json Input constants
	 */
	public static final String CASE_TYPE="case_type";	
	public static final String CASE_ORIGIN="case_origin";
	public static final String PRIORITY="priority";
	public static final String CASE_REASON="case_reason";
	public static final String SUBJECT="subject";
	/**
	 * Solutions
	 * Json Input constants
	 */
	public static final String SOLUTION_TITLE="solution_title";	
	public static final String QUESTION="question";
	public static final String ANSWER="answer";
	/**
	 * Tasks
	 * Json Input constants
	 */
	public static final String DUE_DATE="due_date";
	/**
	 * Events
	 * Json Input constants
	 */
	public static final String START_DATE_TIME="start_datetime";
	public static final String END_DATE_TIME="end_datetime";
	public static final String VENUE="venue";
	/**
	 * Calls
	 * Json Input constants
	 */
	public static final String CALL_FROM_TO="call_from/to";
	public static final String CONTACT_NAME="contact_name";
	public static final String CALL_START_TIME="call_start_time";
	public static final String CALL_DURATION="call_duration";
	public static final String CALL_TYPE="call_type";
	
}
