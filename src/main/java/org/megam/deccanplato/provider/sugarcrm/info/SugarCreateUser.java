package org.megam.deccanplato.provider.sugarcrm.info;

import java.util.ArrayList;

import org.megam.deccanplato.provider.crm.controller.CRMController;
import org.megam.deccanplato.provider.crm.test.SugarAdapterTest;

public class SugarCreateUser {
	
	private String session="tnogfd5sgv263jrcb1bg6vcfl4";
	private String module_name="Users";
	java.util.List<Object> name_value_list=new ArrayList<Object>();
	public SugarCreateUser(){
		
		
		User U=new User();
		U.setName("user_name");
		U.setValue("AB");
		name_value_list.add(U);
		User U1=new User();
		U1.setName("last_name");
		U1.setValue("Deviliaris");
		name_value_list.add(U1);
		User U2=new User();
		U2.setName("emailAddress");
		U2.setValue("raja.pan123@yahoo.com");
		name_value_list.add(U2);
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
