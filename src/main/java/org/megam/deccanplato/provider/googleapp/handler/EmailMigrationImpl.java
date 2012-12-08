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
import java.util.HashMap;
import java.util.Map;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.googleapp.info.AppsForYourDomainMigrationClient;

/**
 * 
 * @author pandiyaraja
 * 
 * This class implements the business activity of GoogleApp Email migration method.
 * this class is implemented by using google-gdata-client library, and this class needs 
 * admin user name, password,domain, destination username and application name to get authenticate 
 * this class has only one methods, to implement business function migrate.
 */
public class EmailMigrationImpl implements BusinessActivity{
    
	private AppsForYourDomainMigrationClient migrate;
	private BusinessActivityInfo bizInfo;
	private Map<String, String> args = new HashMap<String, String>();
	/**
	 * this method initialize the operation to perform migrattion
	 * authentication set in this method by calling AppsForYourDomainMigrationClient class's Constructor
	 * and the credential is stored in object migrate
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		
		this.bizInfo=tempBizInfo;
		this.args=tempArgs;
		try {
				migrate = new AppsForYourDomainMigrationClient(args.get(ADMIN_EMAIL),
					args.get(ADMIN_PASSWORD), args.get(DOMAIN), args.get(DEST_USER), args.get(APPLICATION_NAME));
			} catch (Exception e) {
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
		case MIGRATE:
			outMap=emailMigration(outMap);
			break;
		default:
			break;
		}
		return outMap;
	}

	/**
	 * this method migrates email in a domain
	 * args map has all the details to migrate an email
	 * we can migrate multiple email in a same operation by this method
	 * @param outMap
	 * @return outMap
	 */
	private Map<String, String> emailMigration(Map<String, String> outMap) {
		String rfcTxt = null;
		try {
			rfcTxt=migrate.readFile(args.get(RFCTEXT_PATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//migrate.migrateEmail(rfcTxt);
		outMap.put(OUTPUT, MIGRATE_STRING);
		return outMap;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "emailmigration";
	}

}
