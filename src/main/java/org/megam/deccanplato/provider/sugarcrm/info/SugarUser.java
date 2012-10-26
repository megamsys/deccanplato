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

public class SugarUser {
	
	     Map<String, String> user_auth=new HashMap<String, String>();
	     private String application_name="sugar";
	     private String[]  name_value_list = new String[0];
	     
	     public SugarUser(){
	    	
	    	 
	    	 user_auth.put("user_name", "admin");
	    	 user_auth.put("password", "3ec4036a9c2e75778a63011ca1d47079");
	    	 user_auth.put("version", "1");  
	    	 
	    	 
	    	 
	     }     


}
