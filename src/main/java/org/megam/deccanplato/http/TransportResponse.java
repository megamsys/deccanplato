/** Copyright 2012 Megam systesms
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 **/
 
package org.megam.deccanplato.http;

import java.io.IOException;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;

/**
 * @author ram
 *
 */
public class TransportResponse {
	
	private String status;
	private String entity;
	private String  locale;
	
	public TransportResponse(StatusLine tempStatus, HttpEntity tempEntity, Locale tempLocale)  throws ParseException, IOException  {
		entity = EntityUtils.toString(tempEntity);
		status = tempStatus.toString();
		locale=tempLocale.getDisplayCountry() + ":" + tempLocale.getDisplayLanguage();
	}
	
	public String entityToString() {
		return entity;
	}
	
	public String statusLineToString() {
		return status;
	}
	
	public String localeToString() {
		return locale;
	}

}
