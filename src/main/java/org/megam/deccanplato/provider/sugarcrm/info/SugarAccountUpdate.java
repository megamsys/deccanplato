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


public class SugarAccountUpdate {

	private String session="stta6o986pub3nek80lr29m757";
	private String module_name="Accounts";
	List<Object> name_value_list=new ArrayList<Object>();
	public SugarAccountUpdate(){		
		
		User U=new User();
		U.setName("id");
		U.setValue("ddbdf390-e049-8529-0039-5080f60c5b16");
		name_value_list.add(U);
		User U1=new User();
		U1.setName("name");
		U1.setValue("ACCount Updated");
		name_value_list.add(U1);
			
	}

	private class User {
		private String name;
		private String value;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}


	}
}
