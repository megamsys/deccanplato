package org.megam.deccanplato.provider.crm.info;

import java.util.ArrayList;



public class SugarAccountCreate {

	private String session="stta6o986pub3nek80lr29m757";
	private String module_name="Accounts";
	java.util.List<Object> name_value_list=new ArrayList<Object>();
	public SugarAccountCreate(){		
		
		User U=new User();
		U.setName("name");
		U.setValue("AB Account");
		name_value_list.add(U);
			
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
