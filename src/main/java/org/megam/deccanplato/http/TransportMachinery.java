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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.amazonaws.services.s3.model.PartETag;

public class TransportMachinery {

	public static TransportResponse post(TransportTools nuts)
			throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(nuts.urlString());
		System.out.println("NUTS"+nuts.toString());
		if (nuts.headers() != null) {
			for (Map.Entry<String, String> headerEntry : nuts.headers().entrySet()) {
				
				//this if condition is set for twilio Rest API to add credentials to DefaultHTTPClient, conditions met twilio work.
				if(headerEntry.getKey().equalsIgnoreCase("provider") & headerEntry.getValue().equalsIgnoreCase(nuts.headers().get("provider"))) {
					httpclient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials(nuts.headers().get("account_sid"), nuts.headers().get("oauth_token")));
				}
				//this else part statements for other providers
				else {
				    httppost.addHeader(headerEntry.getKey(), headerEntry.getValue());				    
				}
			}
		}
		if(nuts.fileEntity()!=null) {
			httppost.setEntity(nuts.fileEntity());
		}
		if (nuts.pairs() != null && (nuts.contentType() ==null)) {
			httppost.setEntity(new UrlEncodedFormEntity(nuts.pairs()));			
		}
		
		if (nuts.contentType() !=null) {
			httppost.setEntity(new StringEntity(nuts.contentString(),nuts.contentType()));			
		}
		TransportResponse transportResp = null;
		System.out.println(httppost.toString());
		try {
			HttpResponse httpResp  = httpclient.execute(httppost);
			transportResp = new TransportResponse(httpResp.getStatusLine(),
			httpResp.getEntity(), httpResp.getLocale());
		} finally {
			httppost.releaseConnection();
		}
		return transportResp;

	}
	
	public static TransportResponse put(TransportTools nuts)
			throws ClientProtocolException, IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPut httpput = new HttpPut(nuts.urlString());

		if (nuts.headers() != null) {
			for (Map.Entry<String, String> headerEntry : nuts.headers().entrySet()) {
				httpput.addHeader(headerEntry.getKey(), headerEntry.getValue());
			}
		}
		
		if (nuts.pairs() != null && (nuts.contentType() ==null)) {
			httpput.setEntity(new UrlEncodedFormEntity(nuts.pairs()));
		}
		
		if (nuts.contentType() !=null) {
			httpput.setEntity(new StringEntity(nuts.contentString(),nuts.contentType()));
		}

		TransportResponse transportResp = null;
		try {
			HttpResponse httpResp  = httpclient.execute(httpput);
			transportResp = new TransportResponse(httpResp.getStatusLine(),
					httpResp.getEntity(), httpResp.getLocale());
		} finally {
			httpput.releaseConnection();
		}
		return transportResp;

	}

	public static TransportResponse patch(TransportTools nuts)
			throws ClientProtocolException, IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPatch httppatch = new HttpPatch(nuts.urlString());

		if (nuts.headers() != null) {
			for (Map.Entry<String, String> headerEntry : nuts.headers().entrySet()) {
				httppatch.addHeader(headerEntry.getKey(), headerEntry.getValue());
			}
		}
		
		if (nuts.pairs() != null && (nuts.contentType() ==null)) {
			httppatch.setEntity(new UrlEncodedFormEntity(nuts.pairs()));
		}
		
		if (nuts.contentType() !=null) {
			httppatch.setEntity(new StringEntity(nuts.contentString(),nuts.contentType()));
		}

		TransportResponse transportResp = null;
		try {
			  httpclient.execute(httppatch);
			//transportResp = new TransportResponse(httpResp.getStatusLine(), httpResp.getEntity(), httpResp.getLocale());
		} finally {
			httppatch.releaseConnection();
		}
		return transportResp;

	}
	
	public static TransportResponse get(TransportTools nuts) throws URISyntaxException, ClientProtocolException, IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(nuts.urlString());
		if (nuts.headers() != null) {
			for (Map.Entry<String, String> headerEntry : nuts.headers().entrySet()) {
				
				//this if condition is set for twilio Rest API to add credentials to DefaultHTTPClient, conditions met twilio work.
				if(headerEntry.getKey().equalsIgnoreCase("provider") & headerEntry.getValue().equalsIgnoreCase(nuts.headers().get("provider"))) {
					httpclient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials(nuts.headers().get("account_sid"), nuts.headers().get("oauth_token")));
				}
				//this else part statements for other providers
				else {
				    httpget.addHeader(headerEntry.getKey(), headerEntry.getValue());
				}
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

				//this if condition is set for twilio Rest API to add credentials to DefaultHTTPClient, conditions met twilio work.
				if(headerEntry.getKey().equalsIgnoreCase("provider") & headerEntry.getValue().equalsIgnoreCase(nuts.headers().get("provider"))) {
					httpclient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials(nuts.headers().get("account_sid"), nuts.headers().get("oauth_token")));
				}
				//this else part statements for other providers
				else {
				    httpdel.addHeader(headerEntry.getKey(), headerEntry.getValue());
				}
			}
		}
		
		TransportResponse transportResp = null;
		try {
			  httpclient.execute(httpdel);
			//transportResp = new TransportResponse(httpResp.getStatusLine(), httpResp.getEntity(), httpResp.getLocale());
		} finally {
			httpdel.releaseConnection();
		}
		return transportResp;

	}

}
