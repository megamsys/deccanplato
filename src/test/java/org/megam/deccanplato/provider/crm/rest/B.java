package org.megam.deccanplato.provider.crm.rest;

import java.util.HashMap;
import java.util.Map;

public class B {
	private JsonTest jstest1;
	private String Debug = null;
	private boolean chained=false;
	private C c1;
	private D d1;

	public B() {
		/*d1 = new D();
		c1 = new C();
		jstest1 = new JsonTest();*/
	}

	public void addB(String key, String value) {

		//c.addC(key, value);
		//d.addD(key, value);
		//jstest.add(key, value);
		// System.out.println("In Class B");
	}

	public String toString() {

		return c1.toString() + d1.toString() + jstest1.toString()+"\n"+Debug+"\n"+chained;
	}

}
