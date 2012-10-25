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
import org.apache.http.impl.client.DefaultHttpClient;

public class TransportMachinery {

	public static HttpResponse post(TransportTools nuts)
			throws ClientProtocolException, IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(nuts.urlString());

		if (nuts.headers() != null) {
			for (Map.Entry<String, String> headerEntry : nuts.headers().entrySet()) {
				httppost.addHeader(headerEntry.getKey(), headerEntry.getValue());
			}
		}
		
		if (nuts.pairs() != null) {
			httppost.setEntity(new UrlEncodedFormEntity(nuts.pairs()));
		}

		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} finally {
			httppost.releaseConnection();
		}
		return response;

	}

	public static HttpResponse get(TransportTools nuts) throws URISyntaxException, ClientProtocolException, IOException {

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

		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
		} finally {
			httpget.releaseConnection();
		}

		return response;

	}

	public static HttpResponse delete(TransportTools nuts) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpdel = new HttpDelete(nuts.urlString());

		if (nuts.headers() != null) {
			for (Map.Entry<String, String> headerEntry : nuts.headers().entrySet()) {
				httpdel.addHeader(headerEntry.getKey(), headerEntry.getValue());
			}
		}
		
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpdel);
		} finally {
			httpdel.releaseConnection();
		}
		return response;

	}

}
