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
package org.megam.deccanplato.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

public class TransportTools {

	private String urlString = null;
	private List<NameValuePair> pairs = null;
	private Map<String,String> headers = null;
	private boolean query = false;
	private String encoding = null;
	
	public TransportTools(String urlstr, List<NameValuePair> pair) {
		this(urlstr, pair, null);
	}
	
	public TransportTools(String urlstr, List<NameValuePair> pair,
			Map<String,String> header) {
		this(urlstr, pair, header, false, null);

	}

	public TransportTools(String tempUrlString, List<NameValuePair> tempPair,
			Map<String,String> tempHeader,boolean tempQuery, String tempEncoding) {
		
		this.urlString = tempUrlString;
		this.pairs = tempPair;
		this.headers = tempHeader;
		this.query = tempQuery;
		this.encoding = tempEncoding;
	}

	public URL url() throws MalformedURLException {
		return new URL(urlString());
	}

	public String urlString() {
		return urlString;
	}

	public List<NameValuePair> pairs() {
		return pairs;
	}

	public Map<String,String> headers() {
		return headers;
	}
	
	public boolean isQuery() {
		return query;
	}
	
	public String encoding() {
		return encoding;
	}

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n",
				"*----------------------------------------------*");
		formatter.format("%12s = %s%n", "url", urlString());
		formatter.format("%12s = %s%n", "pairs", pairs());
		formatter.format("%12s = %s%n", "headers", headers());
		formatter.format("%12s = %s%n", "query", isQuery());
		formatter.format("%12s = %s%n", "encoding", encoding());
		formatter.format("%s%n",
				"*----------------------------------------------*");
		formatter.close();
		return strbd.toString();
	}
}
