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
package org.megam.deccanplato.provider.twilio;

/**
 * @author pandiyaraja
 *
 */
public class Constants {
	/**
	 * Common json input constants
	 */
	  public static final String TWILIO_URL="https://api.twilio.com/2010-04-01/";
      public static final String ACCOUNT_SID="account_sid";
      public static final String OAUTH_TOKEN="oauth_token";
      public static final String PROVIDER="provider";
   /**
    * Account
    */
      public static final String FRIENDLY_NAME="friendly_name";	
      public static final String SUB_ACCOUND_SID="sub_account_sid";
      public static final String STATUS="status";
  /**
   * Available Phone Numbers
   */
      public static final String AREA_CODE="area_code";
      public static final String COUNTRY_CODE="country_code";
      public static final String CONTAINS="contains";
      public static final String REGION="region";
      public static final String NEARLATLONG="nearlatlong";
      public static final String DISTANCE="distance";
      public static final String PHONENO="phoneno";
      public static final String OUTGOING_CALLER_SID="outgoing_caller_sid";
  /**
   * Callerid
   */
      public static final String CALL_DELAY="call_delay";
 /**
  * Incoming Phone Numbers
  */
   public static final String API_VERSION="api_versino";
   public static final String INCOMING_PHONE_SID="incoming_phone_sid";
   public static final String VOICE_URL="voice_url";
   public static final String VOICE_METHOD="voice_method";
   public static final String VOICE_FALLBACK_URL="voice_fallback_url";
   public static final String VOICE_FALLBACK_METHOD="voice_fallback_method";
   public static final String STATUS_CALLBACK="status_callback";
   public static final String STATUS_CALLBACK_METHOD="status_callback_method";
   public static final String VOICE_CALLERID_LOOKUP="voice_callerid_lookup";
   public static final String VOICE_APP_SID="voice_app_sid";
   public static final String SMS_URL="sms_url";
   public static final String SMS_METHOD="sms_method";
   public static final String SMS_FALLBACK_URL="sms_fallback_url";
   public static final String SMS_FALLBACK_METHOD="sms_fallback_method";
   public static final String SMS_APP_SID="sms_app_sid";
 /**
  * Sms
  */
   public static final String FROM="from";
   public static final String TO="to";
   public static final String DATE_SENT="date_sent";
   public static final String PAGE_SIZE="page_size";
   public static final String PAGE="page";
   public static final String BODY="body";
   public static final String SMS_SID="sms_sid";
  /**
   * Calls
   */
   public static final String START_TIME="start_time";
   public static final String END_TIME="end_time";
   public static final String CALL_SID="call_sid";
   public static final String URL="url";
   public static final String APPLICATION_SID="application_sid";
   public static final String METHOD="method";
} 
