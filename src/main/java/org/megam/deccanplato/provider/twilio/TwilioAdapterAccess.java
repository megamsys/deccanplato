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
package org.megam.deccanplato.provider.twilio;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.twilio.Constants.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.megam.deccanplato.http.TransportMachinery;
import org.megam.deccanplato.http.TransportResponse;
import org.megam.deccanplato.http.TransportTools;
import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.DefaultDataMap;

/**
 * @author pandiyaraja
 *
 */
public class TwilioAdapterAccess implements AdapterAccess{

    private boolean success = false;
	
	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.core.AdapterAccess#isSuccessful()
	 */
	@Override
	public boolean isSuccessful() {
		return success;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.core.AdapterAccess#authenticate(org.megam.deccanplato.provider.core.DataMap)
	 */
	@Override
	public <T> DataMap<T> authenticate(DataMap<T> accessMap)
			throws AdapterAccessException {
		Map<String, T> access= accessMap.map();
		final String twilioAccount_url="https://"+access.get(ACCOUNT_SID)+":"+access.get(OAUTH_TOKEN)+"@api.twilio.com/2010-04-01/Accounts.json";
                
        TransportTools tools=new TransportTools(twilioAccount_url, null);
        String responseBody = null;
        
        TransportResponse response = null;
        try {
			response=TransportMachinery.get(tools);
			responseBody=response.entityToString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        success=true;
        return accessMap;
	}

}
