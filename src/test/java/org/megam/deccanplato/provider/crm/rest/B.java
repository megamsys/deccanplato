package org.megam.deccanplato.provider.crm.rest;

import java.util.HashMap;
import java.util.Map;

public class B {
	private JsonTest jstest;
	private String Debug = null;
	private boolean chained=false;
	private C c;
	private D d;

	public B() {
		d = new D();
		c = new C();
		jstest = new JsonTest();
	}

	public void addB(String key, String value) {

		c.addC("1", "value2");
		c.addC("2", "value1");
		c.addC("3", "value3");
		c.addC("4", "value4");
		c.addC("5", "value5");
		d.addD(key, value);
		jstest.add(key, value);
		// System.out.println("In Class B");
	}

	public String toString() {

		return c.toString() + d.toString() + jstest.toString()+"\n"+Debug+"\n"+chained;
	}

}
