package org.megam.deccanplato.provider.crm.info;

import java.util.ArrayList;




public class SugarLeadCreate {
  
	private String session="stta6o986pub3nek80lr29m757";
	private String module_name="Leads";
	java.util.List<Object> name_value_list=new ArrayList<Object>();
	public SugarLeadCreate(){		
		
		User U=new User();
		User U1=new User();
		U1.setName("last_name");
		U1.setValue("Deviliaris");
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
