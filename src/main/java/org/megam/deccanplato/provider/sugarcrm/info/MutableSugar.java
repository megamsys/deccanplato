/* “Copyright 2012 Megam Systems”
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.megam.deccanplato.provider.sugarcrm.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import static org.megam.deccanplato.provider.sugarcrm.Constants.*;
/**
 * 
 * @author pandiyaraja
 * 
 * This class populate SugarCRM json input data, and sends to 
 * SugarCRM BusinessActivityImpl class business activity methods create, update and delete.
 *
 */
public class MutableSugar {
		
	private String session;
	private String module_name;
	private List<NameValuePair> name_value_list=new ArrayList<NameValuePair>();
	
	public MutableSugar(String module, Map<String, String> args){
		session=args.get(SESSIONID);
		module_name=getModule(module);
		
		/**
		 * Fields used during creation of common sugar modules
		 */
		 setValue(ID, ID, args);
		 setValue(LAST_NAME, LAST_NAME, args);
		 
		/** 
		 * Fields used during creation of Users
		 */
		 setValue(USER_NAME, USER_NAME, args);
		 setValue(EMAIL, EMAILADD, args);
		 
		 /**
		  * Fields used during creation of Accounts
		  */
		  setValue(NAME,NAME,args);
		  
		  /**
		  * Fields used during creation of Leads
		 */
		  
				
	}
	private void setValue(String jsonKey, String sugarcrmKey, Map<String, String> args) {
		
		if(args.containsKey(jsonKey)) {
			name_value_list.add(new BasicNameValuePair(sugarcrmKey, args.get(jsonKey)));
		}
	}
	/**
	 * This method convert business module to SugarCRM module value
	 * by using some Java.lang package String, String Buffer and Character classes.
	 * it takes input
	 * @param module
	 * @return module name
	 */
	private String getModule(String module) {
		
		StringBuffer modul=new StringBuffer(module);
		char[] mod=new char[10];
		mod=module.toCharArray();
		char c=Character.toUpperCase(mod[0]);
		Character C=new Character(c);
		modul.replace(0, 1, C.toString());
		modul.append("s");
		return modul.toString();
		
	}
	
}
