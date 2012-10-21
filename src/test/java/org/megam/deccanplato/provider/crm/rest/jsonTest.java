package org.megam.deccanplato.provider.crm.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.megam.deccanplato.provider.crm.info.SalesforceCRMLead;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class jsonTest {

	private Map<String, String> access = new HashMap<String, String>();

	public jsonTest() {

	}

	public Map<String, String> asMap() {
		return access;
	}

	@Test
	public void newmap() {
		File file = new File("jas.json");
		String str = null;
		String str1 = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));

			while ((str1 = br.readLine()) != null) {
				str += str1;

			}

			System.out.println(str);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			jsonTest lead = gson.fromJson(str, jsonTest.class);
			System.out.println(lead.asMap());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
