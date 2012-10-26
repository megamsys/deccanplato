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



public class SugaruserList {
	
	private String session="stta6o986pub3nek80lr29m757";
	private String module_name="Leads";
	private String query="";
	private String order_by="";
	private String offset="0";
	java.util.List<Object> select_fields=new ArrayList<Object>();
	public SugaruserList(){		
		
		select_fields.add("id");
		select_fields.add("name");
		select_fields.add("title");
	}
	private String[]  link_name_to_fields_array = new String[0];
	private String max_results="2";
	private String deleted="0";
	private String Favorites="false";
}
