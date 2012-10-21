package org.megam.deccanplato.provider.crm.info;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "Leeds")
public class ZohoCRMLeeds extends ZohoCRMXMLBase {
	
	private static final String SMOWNERID = "SMOWNERID";
	private static final String LEAD_OWNER = "Lead Owner";
	private static final String COMPANY = "Company";
	private static final String FIRST_NAME = "First Name";
	private static final String LAST_NAME = "Last Name";
	private static final String DESIGNATION = "Designation";
	private static final String EMAIL = "Email";
	private static final String PHONE = "Phone";
	private static final String FAX = "Fax";
	private static final String MOBILE = "Mobile";
	private static final String WEBSITE = "Website";
	private static final String LEAD_SOURCE = "Lead Source";
	private static final String LEAD_STATUS = "Lead Status";
	private static final String INDUSTRY = "Industry";
	private static final String NO_OF_EMPLOYEES = "No of Employees";
	private static final String ANNUAL_REVENUE = "Annual Revenue";
	private static final String EMAIL_OPT_OUT = "Email Opt Out";
	private static final String SKYPE_ID = "Skype ID";
	private static final String SALUTATION = "Salutation";
	private static final String STREET = "Street";
	private static final String CITY = "City";
	private static final String STATE = "State";
	private static final String ZIP_CODE = "Zip Code";
	private static final String COUNTRY = "Country";
	private static final String DESCRIPTION = "Description";
	
	public ZohoCRMLeeds() {
		super();
		createRow();		
	}
	
	public void setOwnerId(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(SMOWNERID, val);
	}
	public void setLeadOwner(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(LEAD_OWNER, val);
	}
	public void setCompany(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(COMPANY, val);
	}
	public void setFirstname(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(FIRST_NAME, val);
	}
	public void setLastname(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(LAST_NAME, val);
	}
	public void setDesignation(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(DESIGNATION, val);
	}
	public void setEmail(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(EMAIL, val);
	}
	public void setPhone(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(PHONE, val);
	}
	public void setFax(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(FAX, val);
	}
	public void setMobile(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(MOBILE, val);
	}
	public void setWebsite(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(WEBSITE, val);
	}
	public void setLeadSource(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(LEAD_SOURCE, val);
	}
	public void setLeadStatus(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(LEAD_STATUS, val);
	}
	public void setIndustry(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(INDUSTRY, val);
	}
	public void setNoOfEmployees(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(NO_OF_EMPLOYEES, val);
	} 
	public void setAnualRevenue(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(ANNUAL_REVENUE, val);
	}
	public void setEmailOptOut(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(EMAIL_OPT_OUT, val);
	}
	public void setSkypeId(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(SKYPE_ID, val);
	}
	public void setSalutation(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(SALUTATION, val);
	}
	public void setStreet(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(STREET, val);
	}
	public void setCity(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(CITY, val);
	}
	public void setState(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(STATE, val);
	}
	public void setZipCode(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(ZIP_CODE, val);
	}
	public void setCountry(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(COUNTRY, val);
	}
	public void setDescription(String val) {
		//Add the key,value to the currentRow .
		super.setValueAtCurrentRow(DESCRIPTION, val);
	}

}