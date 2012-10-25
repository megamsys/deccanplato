package org.megam.deccanplato.provider.crm.rest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConvertorTest {
	
	private B b;
	
	@Test
	public void result(){
		
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		b=new B();
		b.addB("1", "Value1");
		
		System.out.println(gson.toJson(b));
	}
		

}
