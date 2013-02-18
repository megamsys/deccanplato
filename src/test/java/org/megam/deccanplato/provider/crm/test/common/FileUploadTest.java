/* 
** Copyright [2012] [Megam Systems]
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
** http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/
package org.megam.deccanplato.provider.crm.test.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.URI;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.junit.Test;

/**
 * @author pandiyaraja
 *
 */
public class FileUploadTest {

	DefaultHttpClient httpClient=new DefaultHttpClient();
	
	String Uri="https://api.box.com/2.0/files/6147287841/content";	
	HttpGet httpPost=new HttpGet(Uri);
	@Test
	public void execute() throws ClientProtocolException, IOException {
		httpPost.setHeader("Authorization", "BoxAuth api_key=bvn29jldy2nnr7l3q03v5k8aalb4utt4&auth_token=34kssk9sxjrv6pliusyf83m58h9ul3sb");
		HttpTrace trace=new HttpTrace(Uri);
		System.out.println(httpClient);
				//httpClient.execute(httpPost);
		HttpResponse resp;
		System.out.println(httpPost.toString());
		resp=httpClient.execute(httpPost);
		
		System.out.println(resp.getStatusLine().getStatusCode()+":::::"+resp.getEntity()+"::::::"+resp.getLocale());
		System.out.println("Location"+resp.getLastHeader("Location").getValue().toLowerCase());
		System.out.println("Location"+resp.getFirstHeader("Location").getValue().toLowerCase());
		if (resp.getStatusLine().getStatusCode() == 302) {
			  String redirectURL = resp.getFirstHeader("Location").getValue();
			  System.out.println(resp.getFirstHeader("Location").getValue());
		}
		
		InputStream in = resp.getEntity().getContent();

        FileOutputStream fos = new FileOutputStream(new File("/home/pandiyaraja/Documents/5979334871.txt"));

        byte[] buffer = new byte[4096];
        int length; 
        while((length = in.read(buffer)) > 0) {
            fos.write(buffer, 0, length);
        }
	}
}
