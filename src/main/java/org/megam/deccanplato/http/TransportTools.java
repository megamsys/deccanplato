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
