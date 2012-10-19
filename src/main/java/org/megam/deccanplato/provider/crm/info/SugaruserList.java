package org.megam.deccanplato.provider.crm.info;

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
