package org.megam.deccanplato.provider.crm.rest;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.megam.core.util.XMLUtils;
import org.megam.deccanplato.provider.zoho.crm.info.Leads;
import org.megam.deccanplato.provider.zoho.crm.info.XMLBase;

public class TempUnitTest {

	@Test
	public void testJacksonXMLGeneration() throws JAXBException {
		Leads leads = new Leads();
		leads.setOwnerId("helloowner");
		leads.setAnualRevenue("1000");
		leads.setCity("MEXICO");
		leads.setCompany("MAX");
		leads.setCountry("DETH VALLY");
		leads.setDescription("account dscription");
		leads.setDesignation("Developer");
		leads.setEmail("raja.pandiya@megam.co.in");
		leads.setEmailOptOut("String");
		leads.setFax("ofx4586465859699");
		leads.setFirstname("Ricky");
		leads.setIndustry("IT");
		leads.setLastname("Ponting");
		leads.setLeadOwner("Mathews");
		leads.setLeadSource("Source");
		leads.setLeadStatus("ACTIVE");
		leads.setMobile("95895746325");
		leads.setNoOfEmployees("10");
		leads.setPhone("012-2548963");
		leads.setSalutation("Salutation");
		leads.setSkypeId("4256gtrm");
		leads.setStreet("Mount vally street");
		leads.setWebsite("megam.co");
		leads.setZipCode("4589632");
		System.out.println(leads.toXMLString());

	}

}
