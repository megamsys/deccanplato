package org.megam.deccanplato.provider.crm.info;

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
