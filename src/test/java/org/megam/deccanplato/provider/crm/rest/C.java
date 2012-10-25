package org.megam.deccanplato.provider.crm.rest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class C {
	
	Map<String, String> mapc = new HashMap<String, String>();
	public void addC(String key, String value) {

		
		mapc.put(key, value);
	}

	public String toString(){
		String key = "";
		String value = "";
		for(Map.Entry<String, String> entry : mapc.entrySet()){
		    key =key+ "key="+entry.getKey()+" "+"Value="+entry.getValue()+",\n";
		    
		    //System.out.println("Key = " + key + ", Value = " + value);
		    
		}
		return key;
	}
}
