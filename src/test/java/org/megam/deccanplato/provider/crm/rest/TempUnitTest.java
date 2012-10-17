package org.megam.deccanplato.provider.crm.rest;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.megam.core.util.XMLUtils;
import org.megam.deccanplato.provider.crm.info.ZohoCRMLeeds;
import org.megam.deccanplato.provider.crm.info.ZohoCRMXMLBase;

public class TempUnitTest {

	@Test
	public void testJacksonXMLGeneration() throws JAXBException {
		ZohoCRMLeeds leads = new ZohoCRMLeeds();
		leads.setOwnerId("helloowner");
		leads.setOwnerId("secondwner");
		System.out.println(XMLUtils.marshalAsString(ZohoCRMXMLBase.class, leads));

	}

}
