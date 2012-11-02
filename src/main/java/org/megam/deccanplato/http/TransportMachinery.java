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

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class TransportMachinery {

	public static TransportResponse post(TransportTools nuts)
			throws ClientProtocolException, IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(nuts.urlString());

		if (nuts.headers() != null) {
			for (Map.Entry<String, String> headerEntry : nuts.headers().entrySet()) {
				httppost.addHeader(headerEntry.getKey(), headerEntry.getValue());
			}
		}
		
		if (nuts.pairs() != null && (nuts.contentType() ==null)) {
			httppost.setEntity(new UrlEncodedFormEntity(nuts.pairs()));
		}
		
		if (nuts.contentType() !=null) {
			httppost.setEntity(new StringEntity(nuts.contentString(),nuts.contentType()));
		}

		TransportResponse transportResp = null;
		try {
			HttpResponse httpResp  = httpclient.execute(httppost);
			transportResp = new TransportResponse(httpResp.getStatusLine(),
					httpResp.getEntity(), httpResp.getLocale());
		} finally {
			httppost.releaseConnection();
		}
		return transportResp;

	}

	public static TransportResponse get(TransportTools nuts) throws URISyntaxException, ClientProtocolException, IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(nuts.urlString());

		if (nuts.headers() != null) {
			for (Map.Entry<String, String> headerEntry : nuts.headers().entrySet()) {
				httpget.addHeader(headerEntry.getKey(), headerEntry.getValue());
			}
		}
		
		if(nuts.isQuery()) {
			URIBuilder uribuilder = new URIBuilder(nuts.urlString());
			uribuilder.setQuery(URLEncodedUtils.format(nuts.pairs(),nuts.encoding()));
			httpget.setURI(uribuilder.build());
		}

		TransportResponse transportResp = null;
		try {
			HttpResponse httpResp  = httpclient.execute(httpget);
			transportResp = new TransportResponse(httpResp.getStatusLine(),
					httpResp.getEntity(), httpResp.getLocale());
		} finally {
			httpget.releaseConnection();
		}

		return transportResp;

	}

	public static TransportResponse delete(TransportTools nuts) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpdel = new HttpDelete(nuts.urlString());

		if (nuts.headers() != null) {
			for (Map.Entry<String, String> headerEntry : nuts.headers().entrySet()) {
				httpdel.addHeader(headerEntry.getKey(), headerEntry.getValue());
			}
		}
		
		TransportResponse transportResp = null;
		try {
			HttpResponse httpResp  = httpclient.execute(httpdel);
			transportResp = new TransportResponse(httpResp.getStatusLine(),
					httpResp.getEntity(), httpResp.getLocale());
		} finally {
			httpdel.releaseConnection();
		}
		return transportResp;

	}

}
