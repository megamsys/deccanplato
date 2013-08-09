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
package org.megam.deccanplato.provider.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.megam.deccanplato.provider.core.CloudMediator;
import org.megam.deccanplato.provider.core.CloudMediatorException;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.core.RequestDataBuilder;
import org.megam.deccanplato.provider.core.SendBackResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.megam.deccanplato.provider.salesforce.crm.info.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class CRM extends Connector {

	private static final String clz = "CRM:";

	private final Logger logger = LoggerFactory.getLogger(CRM.class);

	public CRM() {
	}

	

	@RequestMapping(value = "provider/crm", method = RequestMethod.POST)
	public @ResponseBody
	String create(@RequestBody String inputAsJson)
			throws UnsupportedEncodingException {
		
		
         String decode_input = urlDecoder(inputAsJson);		
		//String str = StringEscapeUtils.escapeHtml4(inputAsJson);
      	//logger.info("\n\n-->"+str);		
		//logger.info("\n\n-->"+StringEscapeUtils.escapeJava(inputAsJson));
		
		logger.info(clz + "create : POST start.\n" + decode_input);

		// This is how the new code will work.

		RequestDataBuilder rdb = new RequestDataBuilder(decode_input);
		RequestData reqdat = rdb.data();
		CloudMediator mediator = mediator(reqdat);		
		SendBackResponse respdat = null;
		try {
			respdat = mediator.handleRequest();
			System.out.println("output json-->"+respdat);
		} catch (CloudMediatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respdat.toString();

	}

	@RequestMapping(value = "provider/crm/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String list(@RequestBody String inputAsJson)
			throws UnsupportedEncodingException {

		  String decode_input = urlDecoder(inputAsJson);	
		logger.info(clz + "List : POST start.\n" + decode_input);

		// This is how the new code will work.

		RequestDataBuilder rdb = new RequestDataBuilder(decode_input);
		RequestData reqdat = rdb.data();
		CloudMediator mediator = mediator(reqdat);
		SendBackResponse respdat = null;
		try {
			respdat = mediator.handleRequest();
			System.out.println("output json-->"+respdat);
		} catch (CloudMediatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return respdat.toString();
	}	
	
}
