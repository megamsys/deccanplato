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
package org.megam.deccanplato.core.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.OAuthServiceProvider;
import net.oauth.client.OAuthClient;
import net.oauth.client.httpclient3.HttpClient3;

/**
 * @author pandiyaraja
 *
 */
public class OAuthHelper {

	private Properties props;
    private File propFile;
    String consumerKey = "R2J6PI2GHOZAQQ1ZZVAWQOC6H2UAKL";
    String callbackUrl = null;
    String consumerSecret = "TG77XPLAGLSGA7LAQIAW5SVA9RQGDT";

    String reqUrl = "https://api.xero.com/oauth/RequestToken";
    String authzUrl = "https://api.xero.com/oauth/Authorize";
    String accessUrl = "https://api.xero.com/oauth/AccessToken";
    String tokenSecret;
    String requestToken;
    String oauth_token;
    String accessToken;
    public static void main(String[] argv) throws Exception {
        OAuthHelper helper=new OAuthHelper();
        helper.requestToken();
        helper.authorize();
        helper.accessToken();
    }   

    private OAuthAccessor createOAuthAccessor(){
        
        //System.out.println("CONSUMERKEY"+consumerKey+"SECRET"+consumerSecret+"REQSTURL"+reqUrl+"authurl"+authzUrl+"accurl"+accessUrl);
        OAuthServiceProvider provider
                = new OAuthServiceProvider(reqUrl, authzUrl, accessUrl);
        OAuthConsumer consumer
                = new OAuthConsumer(callbackUrl, consumerKey,
                consumerSecret, provider);      
               return new OAuthAccessor(consumer);
    }

    private void updateProperties(String msg) throws IOException {
        props.store(new FileOutputStream(propFile), msg);
    }

    private OAuthMessage sendRequest(Map map, String url) throws IOException,
            URISyntaxException, OAuthException
    {
        List<Map.Entry> params = new ArrayList<Map.Entry>();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry p = (Map.Entry) it.next();
            params.add(new OAuth.Parameter((String)p.getKey(),
                    (String)p.getValue()));
        }
        OAuthAccessor accessor = createOAuthAccessor();
        accessor.tokenSecret = tokenSecret;
        OAuthClient client = new OAuthClient(new HttpClient3());
        return client.invoke(accessor, "GET",  url, params);
    }    
     private void requestToken() throws IOException, OAuthException, URISyntaxException{
            OAuthAccessor accessor = createOAuthAccessor();
            OAuthClient client = new OAuthClient(new HttpClient3());
            
            client.getRequestToken(accessor);

            requestToken= accessor.requestToken;
            tokenSecret= accessor.tokenSecret;
            System.out.println(accessor.requestToken); 
            //updateProperties("Last action: added requestToken");
            //System.out.println(propFile.getCanonicalPath() + " updated");
        }
        private void accessToken() throws IOException, URISyntaxException, OAuthException
        {
            Properties paramProps = new Properties();
            paramProps.setProperty("oauth_token",requestToken);
            oauth_token=requestToken;

            OAuthMessage response 
		= sendRequest(paramProps, accessUrl);

            accessToken=oauth_token;
            tokenSecret=response.getParameter("oauth_token_secret");
            System.out.println(tokenSecret);
            //props.setProperty("userId", response.getParameter("user_id"));


            //updateProperties("Last action: added accessToken");
            //System.out.println(propFile.getCanonicalPath() + " updated");
        }
        private void authorize() throws IOException, URISyntaxException, OAuthException
        {
            // just print the redirect
            Properties paramProps = new Properties();
            //paramProps.setProperty("application_name","xero invoice");
            paramProps.setProperty("oauth_token",requestToken);
            oauth_token=requestToken;
             
            OAuthAccessor accessor = createOAuthAccessor();
           
            OAuthMessage response = sendRequest(paramProps, accessor.consumer.serviceProvider.userAuthorizationURL);
            
            System.out.println("Paste this in a browser:");
            System.out.println(response.URL);
        } /*else {
            // access the resource
            Properties paramProps = new Properties();
            oauth_token=accessToken;

            OAuthMessage response = sendRequest(paramProps, operation);
            System.out.println(response.readBodyAsString());
        }*/

    
}
