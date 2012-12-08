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
package org.megam.deccanplato.provider.googleapp.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.googleapp.Constants.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Link;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.City;
import com.google.gdata.data.extensions.Country;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.ExtendedProperty;
import com.google.gdata.data.extensions.FamilyName;
import com.google.gdata.data.extensions.FormattedAddress;
import com.google.gdata.data.extensions.FullName;
import com.google.gdata.data.extensions.GivenName;
import com.google.gdata.data.extensions.Im;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.data.extensions.PostCode;
import com.google.gdata.data.extensions.Region;
import com.google.gdata.data.extensions.Street;
import com.google.gdata.data.extensions.StructuredPostalAddress;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * 
 * @author pandiyaraja
 * 
 * This class implements the business activity of GoogleApp contacts method.
 * this class is implemented by using google-gdata-client library, and this class needs 
 * client user name, password and application name to get authenticate 
 * this class has four methods, to implement business functions, like create, update,
 * lisd and delete.
 */
public class ContactImpl implements BusinessActivity{
	private ContactsService service; 
	private BusinessActivityInfo bizInfo;
	private Map<String, String> args = new HashMap<String, String>();
	/**
	 * this method initialize the operations to perform (like create, list, update, delete)
	 * authentication set in this method by calling ContactsService class's setUserCredentials
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.bizInfo=tempBizInfo;
		this.args=tempArgs;
		service = new ContactsService(args.get(APPLICATION_NAME));
		try {
			service.setUserCredentials(args.get(EMAIL_ID), args.get(PASSWORD));
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#run()
	 */
	@Override
	public Map<String, String> run() {
		Map<String, String> outMap = new HashMap<String, String>();
		switch (bizInfo.getActivityFunction()) {
		case CREATE:
			outMap = create(outMap);
			break;
		case LIST:
			outMap = list(outMap);
			break;
		case UPDATE:
			outMap = update(outMap);
			break;
		case DELETE:
			outMap = delete(outMap);
			break;
		default:
			break;
		}

		return outMap;
	}

	/**
	 * this method creates a contact in a user account.
	 * args map has all the value to create a contact in a user account,
	 * contacts create by calling ContactsService class's insert method 
	 * with client credential service
	 * @param outMap
	 * @return outMap(detais of created contact)
	 */
	private Map<String, String> create(Map<String, String> outMap) {
		
		// Create the entry to insert.
		  ContactEntry contact = new ContactEntry();
		  // Set the contact's name.
		  Name name = new Name();
		  final String NO_YOMI = null;
		  name.setFullName(new FullName(args.get(FULL_NAME), NO_YOMI));
		  name.setGivenName(new GivenName(args.get(GIVEN_NAME), NO_YOMI));
		  name.setFamilyName(new FamilyName(args.get(FAMILY_NAME), NO_YOMI));
		  contact.setName(name);
		  contact.setContent(new PlainTextConstruct(NOTES));
		  // Set contact's e-mail addresses.
		  Email primaryMail = new Email();
		  primaryMail.setAddress(args.get(PRIMARY_EMAIL));
		  primaryMail.setDisplayName(args.get(DISPLAY_NAME));
		  primaryMail.setRel(PRIMARY_REL);
		  primaryMail.setPrimary(true);
		  contact.addEmailAddress(primaryMail);
		  Email secondaryMail = new Email();
		  secondaryMail.setAddress(args.get(SECOND_EMAIL));
		  secondaryMail.setRel(WORK_REL);
		  secondaryMail.setPrimary(false);
		  contact.addEmailAddress(secondaryMail);
		  // Set contact's phone numbers.
		  PhoneNumber primaryPhoneNumber = new PhoneNumber();
		  primaryPhoneNumber.setPhoneNumber(args.get(PRIMARY_PHONE));
		  primaryPhoneNumber.setRel(WORK_REL);
		  primaryPhoneNumber.setPrimary(true);
		  contact.addPhoneNumber(primaryPhoneNumber);
		  PhoneNumber secondaryPhoneNumber = new PhoneNumber();
		  secondaryPhoneNumber.setPhoneNumber(args.get(SECOND_PHONE));
		  secondaryPhoneNumber.setRel(PRIMARY_REL);
		  contact.addPhoneNumber(secondaryPhoneNumber);
		  // Set contact's IM information.
		  Im imAddress = new Im();
		  imAddress.setAddress(args.get(IM_ADDRESS));
		  imAddress.setRel(PRIMARY_REL);
		  imAddress.setProtocol(GTALK_REL);
		  imAddress.setPrimary(true);
		  contact.addImAddress(imAddress);
		  // Set contact's postal address.
		  StructuredPostalAddress postalAddress = new StructuredPostalAddress();
		  postalAddress.setStreet(new Street(args.get(STREET)));
		  postalAddress.setCity(new City(args.get(CITY)));
		  postalAddress.setRegion(new Region(args.get(REGION)));
		  postalAddress.setPostcode(new PostCode(args.get(ZIP_CODE)));
		  postalAddress.setCountry(new Country(args.get(REGION), args.get(COUNTRY)));
		  postalAddress.setFormattedAddress(new FormattedAddress(args.get(ADDRESS)));
		  postalAddress.setRel(WORK_REL);
		  postalAddress.setPrimary(true);
		  contact.addStructuredPostalAddress(postalAddress);
		  // Ask the service to insert the new entry
		  URL postUrl;
		  ContactEntry createdContact = null;
		try {
			postUrl = new URL(PRE_URL+args.get(EMAIL_ID)+POST_URL);
			createdContact = service.insert(postUrl, contact);
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          outMap.put(OUTPUT, CREATE_STRING+createdContact.getId());
		return outMap;
	}

	/**
	 * this method lists all contacts in a user account.
	 * args map has all the value to list contacts in a user account,
	 * contacts list by calling ContactsService class's getFeed method 
	 * with client credential service
	 * @param outMap
	 * @return outMap has the list of contacts and their details
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		
		List<String> list=new ArrayList<>();
		URL feedUrl;
		try {
			feedUrl = new URL(PRE_URL+args.get(EMAIL_ID)+POST_URL);
			ContactFeed resultFeed = service.getFeed(feedUrl,
					ContactFeed.class);
					
			if(resultFeed!=null) {
			  for (ContactEntry entry : resultFeed.getEntries()) {
				  List<NameValuePair> contList=new ArrayList<NameValuePair>();
				  contList.add(new BasicNameValuePair(ID, entry.getId()));
				  if (entry.hasName()) {
	               Name name=entry.getName();
				  if (name.hasFullName()) {
				        String fullNameToDisplay = name.getFullName().getValue();
				        contList.add(new BasicNameValuePair(FULLNAME, fullNameToDisplay));
				        if (name.getFullName().hasYomi()) {
				          fullNameToDisplay += " (" + name.getFullName().getYomi() + ")";
				          
				        }
				      } 
				      if (name.hasNamePrefix()) {
				       String prefix=name.getNamePrefix().getValue();
				       contList.add(new BasicNameValuePair("nameprefix", prefix));
				      } 
				      if (name.hasGivenName()) {
				        String givenNameToDisplay = name.getGivenName().getValue();
				        contList.add(new BasicNameValuePair(GIVENNAME, givenNameToDisplay));
				        if (name.getGivenName().hasYomi()) {
				          givenNameToDisplay += " (" + name.getGivenName().getYomi() + ")";
				          
				        }
				      }
				      if (name.hasAdditionalName()) {
				        String additionalNameToDisplay = name.getAdditionalName().getValue();
				        if (name.getAdditionalName().hasYomi()) {
				          additionalNameToDisplay += " (" + name.getAdditionalName().getYomi() + ")";
				        }
				      }
				      if (name.hasFamilyName()) {
				        String familyNameToDisplay = name.getFamilyName().getValue();
				        contList.add(new BasicNameValuePair(FAMILYNAME, familyNameToDisplay));
				        if (name.getFamilyName().hasYomi()) {
				          familyNameToDisplay += " (" + name.getFamilyName().getYomi() + ")";				          
				        }
				      if (name.hasNameSuffix()) {
				        contList.add(new BasicNameValuePair("namesufix", name.getNameSuffix().getValue()));
				      } 
			        }
				    for (Email email : entry.getEmailAddresses()) {
				    	contList.add(new BasicNameValuePair(EMAIL, email.getAddress()));				     
				      if (email.getRel() != null) { 
				    	  email.getRel();				      
				      }
				      if (email.getLabel() != null) {
				        email.getLabel();
				      }
				      if (email.getPrimary()) {
				        
				      }
				      
				    }
				    for (Im im : entry.getImAddresses()) {
				    	contList.add(new BasicNameValuePair(IM_ADDRESS, im.getAddress()));
				      if (im.getLabel() != null) {
				        im.getLabel();
				      }
				      if (im.getRel() != null) {
				        im.getRel();
				      }
				      if (im.getProtocol() != null) {
				        im.getProtocol();
				      }
				      if (im.getPrimary()) {
				        
				      }
				      
				    }
				    for (GroupMembershipInfo group : entry.getGroupMembershipInfos()) {
				    	contList.add(new BasicNameValuePair(GROUP, group.getHref()));
				      
				    }
				    for(PhoneNumber phone : entry.getPhoneNumbers()) {
				    	contList.add(new BasicNameValuePair(PHONE, phone.getPhoneNumber()));
				    }
				    for (ExtendedProperty property : entry.getExtendedProperties()) {
				      if (property.getValue() != null) {
				              property.getValue();
				      } else if (property.getXmlBlob() != null) {
				              property.getXmlBlob().getBlob();
				      }
				    }
				    Link photoLink = entry.getContactPhotoLink();
				    String photoLinkHref = photoLink.getHref();				   
				    if (photoLink.getEtag() != null) {
				      photoLink.getEtag();
				    }
				    list.add(contList.toString());
				    outMap.put(OUTPUT,list.toString());
			  }
			}
		}
	}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outMap;
	}

	/**
	 * this method updates a contact in a user account.
	 * args map has all the value to update contact in a user account,
	 * contact update by calling ContactsService class's getEntry method 
	 * with client credential service
	 * @param outMap
	 * @return outMap has the updated contact details
	 */
	private Map<String, String> update(Map<String, String> outMap) {
		
		URL postUrl;
		URL editUrl;
		 	
		 ContactEntry entryToUpdate;
		try {
			postUrl = new URL(PRE_URL+args.get(EMAIL_ID)+POST_URL+args.get(ID));
			entryToUpdate = service.getEntry(postUrl, ContactEntry.class);
			
             if(entryToUpdate.hasName()) {
            	 Name name=entryToUpdate.getName();
            	 if(name.hasFullName()) {
            		 entryToUpdate.getName().getFullName().setValue(args.get(FULL_NAME));
            	 }
            	 if(name.hasGivenName()) {
            		 entryToUpdate.getName().getGivenName().setValue(args.get(GIVEN_NAME));
            	 }
            	 if(name.hasFamilyName()) {
			         entryToUpdate.getName().getFamilyName().setValue(args.get(FAMILY_NAME));
            	 }
             }             
			  editUrl = new URL(entryToUpdate.getEditLink().getHref());
			  
			  ContactEntry contactEntry = service.update(editUrl, entryToUpdate);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ServiceException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		outMap.put(OUTPUT, UPDATE_STRING+args.get(ID));
		return outMap;
	}

	/**
	 * this method deletes a contact in a user account.
	 * args map has all the value to update contact in a user account,
	 * contact delete by calling ContactsService class's getEntry method 
	 * with client credential service
	 * @param outMap
	 * @return outMap has the deleted contact id
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		
		URL postUrl;
		try {
			postUrl = new URL(PRE_URL+args.get(EMAIL_ID)+POST_URL+args.get(ID));
			ContactEntry contact = service.getEntry(postUrl, ContactEntry.class);
			 contact.delete();
			 outMap.put(OUTPUT, DELETE_STRING+contact.getId());             
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		 		  
		return outMap;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "contact";
	}

}
