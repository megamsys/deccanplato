package org.megam.deccanplato.provider.crm.info;

import java.util.ArrayList;
import java.util.List;



public class SugarleadUpdate {

	private String session="stta6o986pub3nek80lr29m757";
	private String module_name="Leads";
	List<Object> name_value_list=new ArrayList<Object>();
	public SugarleadUpdate(){		
		
		User U=new User();
		U.setName("id");
		U.setValue("2893e644-013d-416d-3561-508105d5e49c");
		name_value_list.add(U);
		User U1=new User();
		U1.setName("last_name");
		U1.setValue("Leads Updated");
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
