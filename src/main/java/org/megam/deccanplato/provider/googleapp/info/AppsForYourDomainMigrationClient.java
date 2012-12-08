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
package org.megam.deccanplato.provider.googleapp.info;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gdata.client.appsforyourdomain.migration.MailItemService;
import com.google.gdata.data.appsforyourdomain.migration.Label;
import com.google.gdata.data.appsforyourdomain.migration.MailItemEntry;
import com.google.gdata.data.appsforyourdomain.migration.MailItemFeed;
import com.google.gdata.data.appsforyourdomain.migration.MailItemProperty;
import com.google.gdata.data.appsforyourdomain.migration.Rfc822Msg;
import com.google.gdata.data.batch.BatchStatus;
import com.google.gdata.data.batch.BatchUtils;
import com.google.gdata.util.ServiceException;

/**
 * @author pandiyaraja
 *
 */
public class AppsForYourDomainMigrationClient {
		
		  // Number of email messages to insert.
		  private static final int ITEMS_TO_BATCH = 5;  
		  
		  private static final String MIGRATED_LABEL = "Migrated Email";
		  
		  private final String domain;
		  private final String destinationUser;
		  private final String userName;
		  
		  private final MailItemService mailItemService;


		  /**
		   * Constructs an AppsForYourDomainMigrationClient for the given domain using
		   * the given admin credentials.
		   * 
		   * @param username The username of a domain user or administrator
		   * @param password The user's password on the domain
		   * @param domain The domain in which mail is being migrated
		   * @param destinationUser the destination user to whom mail should be migrated
		   */
		  public AppsForYourDomainMigrationClient(String username, String password,
		      String domain, String destUser, String application) throws Exception {

		    this.domain = domain;	
		    this.userName=username;
		    this.destinationUser = destUser;
		    

		    // Set up the mail item service.
		    mailItemService = new MailItemService(application);
		    mailItemService.setUserCredentials(username, password);

		    // Run the sample.
		    //runSample();
		  }

		  /**
		   * Main driver for the sample; migrates a batch feed of email messages
		   * and prints the results.
		   */
		  public String migrateEmail(String rfcTxt) {
		    
		      // Create labels for mail items to be inserted.
		    List<String> labels = new ArrayList<String>();
		    labels.add(MIGRATED_LABEL);

		    // Set properties for mail items to be inserted. We want all these
		    // mail items to be unread and sent to the inbox (in addition to being
		    // labeled).
		    List<MailItemProperty> properties = new ArrayList<MailItemProperty>();
		    properties.add(MailItemProperty.UNREAD);
		    properties.add(MailItemProperty.INBOX);

		    MailItemEntry[] entries = new MailItemEntry[ITEMS_TO_BATCH];
		    for (int i = 0; i < entries.length; i++) {
		      entries[i] = setupMailItem(rfcTxt, properties, labels);
		    }

		    // Send several emails in a batch.
		    String retStr = null;
		    try {
		      MailItemFeed feed = batchInsertMailItems(entries);

		      // Check for failure in the returned entries.
		      int failedInsertions = 0, successfulInsertions = 0;
		      for (MailItemEntry returnedEntry : feed.getEntries()) {
		        if (BatchUtils.isFailure(returnedEntry)) {
		          BatchStatus status = BatchUtils.getBatchStatus(returnedEntry);
		          
		          failedInsertions++;
		        } else {
		          successfulInsertions++;
		        }
		      }		              
		       retStr="SuccessFull Insertion"+Integer.toString(successfulInsertions)+", Failed Insertion"+Integer.toString(failedInsertions);
		    } catch (IOException e) {
		        e.printStackTrace();
		    } catch (ServiceException e) {
		        e.printStackTrace();
		    }
		    
			return retStr;
		  }

		  /**
		   * Inserts one or more MailItem entries in a single batch operation. Using
		   * batch insertion is helpful in reducing HTTP overhead.
		   * 
		   * @param mailItems one or more {@link MailItemEntry} objects
		   * @return a feed with the result of each operation in a separate
		   *         {@link MailItemEntry} object.
		   * @throws IOException if an error occurs while communicating with the GData
		   *         service.
		   * @throws ServiceException if the insert request failed due to system error.
		   */  
		  private MailItemFeed batchInsertMailItems(MailItemEntry ... mailItems)
		      throws ServiceException, IOException {
		    		    
		    MailItemFeed feed = new MailItemFeed();
		    for (int i = 0; i < mailItems.length; i++) {
		      BatchUtils.setBatchId(mailItems[i], Integer.toString(i));
		      feed.getEntries().add(mailItems[i]);
		    }
		    
		    return mailItemService.batch(domain, destinationUser, feed);
		  }
		  
		  /**
		   * Helper method to read a file and return its contents as a text string,
		   * with lines separated by "\r\n" (CR+LF) style newlines.
		   * @param location the path to the file
		   * @return the String contents of the file
		   * @throws IOException if an error occurs reading the file
		   */
		  public String readFile(String location) throws IOException {
		    FileInputStream is = new FileInputStream(new File(location)); 
		    BufferedReader br = new BufferedReader(new InputStreamReader(is));
		    StringBuffer fileContents = new StringBuffer();

		    String newLine = br.readLine();
		    while (newLine != null) {
		      fileContents.append(newLine);
		      fileContents.append("\r\n");

		      newLine = br.readLine();        
		    }
		      
		    br.close();
		    
		    return fileContents.toString();		   
		  }
		  
		  /**
		   * Helper method to set up a new MailItemEntry with the given values.
		   * 
		   * @param rfcText the RFC822 text of the message
		   * @param properties a list of properties to be applied to the message
		   * @param labels a list of labels the message should have when inserted into
		   * Gmail
		   * @return the {@code MailItemEntry} set up with the message, labels and
		   * properties
		   */
		  private MailItemEntry setupMailItem(String rfcText,
		      List<MailItemProperty> properties, List<String> labels) {

		    // Unique subject and message id are required so that GMail does not
		    // suppress duplicate messages
		    String randomFactor = Integer.toString(100000 + 
		        (new Random()).nextInt(900000));
		    rfcText = rfcText.replace("Subject: Subject",
		        "Subject: Unique Subject " + randomFactor);
		    rfcText = rfcText.replace("Message-ID: <", "Message-ID: <" + randomFactor);
		    Rfc822Msg rfcMsg = new Rfc822Msg(rfcText);
		    
		    // create MailItemEntry with appropriate data
		    MailItemEntry mailItem = new MailItemEntry();
		    mailItem.setRfc822Msg(rfcMsg);
		    for (String label : labels) {
		      mailItem.addLabel(new Label(label));
		    }
		    
		    for (MailItemProperty property : properties) {
		      mailItem.addMailProperty(property);
		    }
		    
		    return mailItem;
		  }
		  
		  	  
}
