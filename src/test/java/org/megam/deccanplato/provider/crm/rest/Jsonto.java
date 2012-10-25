package org.megam.deccanplato.provider.crm.rest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Jsonto {

	@Test
	public void jsontoTest() {
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
		B b = gson.fromJson(str, B.class);
		System.out.println("MAPVALUE:" + b.toString());
		// System.out.println("MAPVALUE:" + gson.toJson(b));
	}
}
