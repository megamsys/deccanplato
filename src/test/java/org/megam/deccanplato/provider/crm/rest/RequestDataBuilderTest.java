package org.megam.deccanplato.provider.crm.rest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import org.junit.Test;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.core.RequestDataBuilder;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class RequestDataBuilderTest {

	//@Test
	public void jsontoTest() {
		String str = "";
		String str1 = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader(
							"/home/pandiyaraja/code/megam/development/deccanplato/src/test/java/org/megam/deccanplato/provider/crm/rest/jas.json"));

			while ((str1 = br.readLine()) != null) {
				str += str1;

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Value" + str);
		RequestDataBuilder RDB=new RequestDataBuilder(str);		
		
	}
	
	@Test
	public void providerRegistryTest(){
		
		GenericApplicationContext ctx = new GenericApplicationContext();
		 XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		 xmlReader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
		 //PropertiesBeanDefinitionReader propReader = new PropertiesBeanDefinitionReader(ctx);
		// propReader.loadBeanDefinitions(new ClassPathResource("otherBeans.properties"));
		 ctx.refresh();

		 ProviderRegistry myBean = (ProviderRegistry) ctx.getBean("providerRegistry");
		 System.out.println("Provider Registry"+myBean.toString());
	}
}
