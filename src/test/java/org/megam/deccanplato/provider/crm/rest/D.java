package org.megam.deccanplato.provider.crm.rest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class D {

	Map<String, String> mapd = new HashMap<String, String>();
	public void addD(String key, String value) {

		
		mapd.put(key, value);
	}
	public String toString(){
		String key = "";
		String value = "";
		for(Map.Entry<String, String> entry : mapd.entrySet()){
			key =key+ "key="+entry.getKey()+" "+"Value="+entry.getValue()+",\n";
		    //System.out.println("Key = " + key + ", Value = " + value);
		}
		return key;
	}
}
