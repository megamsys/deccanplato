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
import static org.megam.deccanplato.provider.sugarcrm.Constants.*;
/**
 * 
 * @author pandiyaraja
 *
 * This class populate SugarCRM json input data, and sends to 
 * SugarCRM BusinessActivityImpl class business activity method list.
 */
public class ImmutableSugar {
	
	private String session;
	private String module_name;
	private String query="";
	private String order_by="";
	private String offset="0";
	private List<String> select_fields=new ArrayList<String>();
	public ImmutableSugar(String module, Map<String, String> args){		
		session=args.get(SESSIONID);
		module_name=getModule(module);
		
		select_fields.add(ID);
		select_fields.add(NAME);
		select_fields.add(TITLE);
		select_fields.add("date_start");		
	}
	private String[]  link_name_to_fields_array = new String[0];
	private String max_results="2";
	private String deleted="0";
	private String Favorites="false";
	
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
