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
package org.megam.deccanplato.provider.zoho.invoice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

public class AccessParser {
	private static final String AUTHTOKEN="AUTHTOKEN";
	private String auth_token="";
	
	public AccessParser(String token) throws IOException{		
		BufferedReader reader=new BufferedReader(new StringReader(token));
		String result=null;
		while((result=reader.readLine())!=null){
			if(result.trim().startsWith(AUTHTOKEN)){
				StringTokenizer strk=new StringTokenizer(result,"=");
				strk.nextToken();
				auth_token=strk.nextToken();
			}
		}
	}

	public String getAuthtoken(){
		return auth_token;
	}
	public String toString(){
		
		return(getAuthtoken());
		
	}
}
