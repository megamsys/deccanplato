package org.megam.deccanplato.provider.crm.rest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;
import org.megam.deccanplato.provider.crm.info.ZohoLeads;

public class TempUnitTest {

	@Test
	public void testJacksonXMLGeneration() throws JAXBException {
		ZohoLeads leads = new ZohoLeads();
		leads.setOwnerId("helloowner");
		leads.setOwnerId("secondwner");


		JAXBContext context = JAXBContext.newInstance(ZohoLeads.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(leads, System.out);

	}

}
