package org.megam.deccanplato.provider.crm.rest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.google.api.client.json.JsonParser;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class JsonTest {

	private Map<String, String> access = new HashMap<String, String>();

	public void add(String key, String value) {

		   access.put(key, value);
		   //System.out.println(access);
	}
	

	 @Test
	public void newmap() {
		// File file = new File("jas.json");
		String str = "";
		String str1 = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader(
							"/home/pandiyaraja/code/megam/development/deccanplato/src/test/java/org/megam/deccanplato/provider/crm/rest/jas.json"));

			while ((str1 = br.readLine()) != null) {
				// System.out.println(str1);
				str += str1;

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Value" + str);
		Gson gson = new Gson();
		Type Map = new TypeToken<Map<String, String>>() {
		}.getType();
		Map<String, String> lead = gson.fromJson(str, Map);
		System.out.println("MAPVALUE:" + lead);
		System.out.println("MAPVALUE:" + lead.get("api_token"));

	}
	public String toString(){
		String key = "";
		String value = "";
		for(Map.Entry<String, String> entry : access.entrySet()){
			key =key+ "key="+(String)entry.getKey()+" "+"Value="+(String)entry.getValue()+",\n";
		    //System.out.println("Key = " + key + ", Value = " + value);
		}
		return key;
	}
}
