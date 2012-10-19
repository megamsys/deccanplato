package org.megam.deccanplato.provider.crm.info;

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
