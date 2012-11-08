/**
 * “Copyright 2012 Megam Systems”
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.megam.deccanplato.provider.crm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This is a temporary class to test timezon, Gson/Jackson toJson. This will get nuked eventually.
 * @author pandiyaraja
 * 
 */
public class TimezoneTest {
	//@Test
	public void timeTest() {
		Locale locale = new Locale("en", "US");
		String time = locale.toString();
		System.out.println("Time" + time);
		System.out.println("TimeZONE" + SimpleTimeZone.getTimeZone(time));
	}

	//@Test
	public void timeObjMapper() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper obj = new ObjectMapper();
		List<NameValuePair> userAttrList = new ArrayList<NameValuePair>();
		userAttrList.add(new BasicNameValuePair("Username", "john"));
		userAttrList.add(new BasicNameValuePair("FirstName", "first"));
		System.out.println(obj.writeValueAsString(userAttrList));
		System.out.println("----------");

	}
	
	//@Test
	public void timeUsingGsonMap()  {
		Gson obj = new GsonBuilder().setPrettyPrinting().create();
		List<NameValuePair> userAttrList = new ArrayList<NameValuePair>();
		userAttrList.add(new BasicNameValuePair("Username", "john"));
		userAttrList.add(new BasicNameValuePair("FirstName", "first"));
		System.out.println(obj.toJson(userAttrList));

	}
	@Test
	public void chartest() {
		String module="user";
		StringBuffer modul=new StringBuffer(module);
		char[] mod=new char[10];
		mod=module.toCharArray();
		char c=Character.toUpperCase(mod[0]);
		//String C=new String(c);
		Character C=new Character(c);
		//C.toString();
		modul.replace(0, 1, C.toString());
		modul.append("s");
		System.out.println(modul.toString());
	
		
	}
}
