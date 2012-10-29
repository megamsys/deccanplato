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

import java.util.HashMap;
import java.util.Map;

import org.megam.deccanplato.provider.core.DataMap;

public class SugarUser {
	
	     Map<String, String> user_auth=new HashMap<String, String>();
	     private String application_name="sugar";
	     private String[]  name_value_list = new String[0];
	     
	     public <T> SugarUser(DataMap<T> dmap){
	    	
	    	 
	    	 user_auth.put("user_name", (String) dmap.map().get("access_username"));
	    	 user_auth.put("password", (String) dmap.map().get("access_password"));
	    	 user_auth.put("version", "1");
	    	 
	    	 
	    	 
	     }     


}
