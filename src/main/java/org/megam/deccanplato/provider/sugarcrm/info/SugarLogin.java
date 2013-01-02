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
package org.megam.deccanplato.provider.sugarcrm.info;

import java.util.HashMap;
import java.util.Map;

import org.megam.deccanplato.provider.core.DataMap;
import static org.megam.deccanplato.provider.sugarcrm.Constants.*;

/**
 * @author pandiyaraja
 *
 *this method populate json data to send as input of SugarCRM login method
 */
public class SugarLogin {

	  Map<String, String> user_auth=new HashMap<String, String>();
      private String application_name=APPLICATION;
      private String[]  name_value_list = new String[0];
      public <T extends Object> SugarLogin(Map<String, T> accessMap){
    	  
          user_auth.put("user_name", (String)accessMap.get(ADMIN_USER_NAME));
          user_auth.put("password", (String)accessMap.get(PASSWORD));
          user_auth.put("version", (String)accessMap.get(VERSION));  
      }
}
