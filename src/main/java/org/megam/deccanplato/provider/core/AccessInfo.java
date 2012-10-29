/* “Copyright 2012 Megam Systems”
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.megam.deccanplato.provider.core;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import org.megam.core.api.secure.AccessToken;

public class AccessInfo implements DataMap {

	private static final String API_TOKEN="api_token";
	private static final String ACCESS_EMAIL="access_email";
	private Map<String, String> access = new HashMap<String, String>();
	private AccessToken token;

	public AccessInfo() {
		token=new AccessToken();
	}


	public Map<String, String> map() {
		return access;
	}

	public AccessToken token() {
		token.setToken(access.get(API_TOKEN));
		token.setEmail(access.get(ACCESS_EMAIL));
		return token;
	}

	public String name() {
		return "access-datamap" ;
			
	}
	

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, String> entry : map().entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		return strbd.toString();
	}

}
