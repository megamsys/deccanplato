/**
 * “Copyright 2012 Megam Systems”
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.megam.deccanplato.provider.crm.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.logging.FileHandler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.w3c.dom.html.HTMLFieldSetElement;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveRequestInitializer;
import com.google.api.services.drive.model.File;
import com.google.gdata.util.ServiceException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This is a temporary class to test timezon, Gson/Jackson toJson. This will get
 * nuked eventually.
 * 
 * @author pandiyaraja
 * 
 */
public class TimezoneTest {
	// @Test
	public void timeTest() {
		Locale locale = new Locale("en", "US");
		String time = locale.toString();
		System.out.println("Time" + time);
		System.out.println("TimeZONE" + SimpleTimeZone.getTimeZone(time));
	}

	// @Test
	public void timeObjMapper() throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper obj = new ObjectMapper();
		List<NameValuePair> userAttrList = new ArrayList<NameValuePair>();
		userAttrList.add(new BasicNameValuePair("Username", "john"));
		userAttrList.add(new BasicNameValuePair("FirstName", "first"));
		System.out.println(obj.writeValueAsString(userAttrList));
		System.out.println("----------");

	}

	// @Test
	public void timeUsingGsonMap() {
		Gson obj = new GsonBuilder().setPrettyPrinting().create();
		List<NameValuePair> userAttrList = new ArrayList<NameValuePair>();
		userAttrList.add(new BasicNameValuePair("Username", "john"));
		userAttrList.add(new BasicNameValuePair("FirstName", "first"));
		System.out.println(obj.toJson(userAttrList));

	}

	// @Test
	public void chartest() {
		String module = "user";
		StringBuffer modul = new StringBuffer(module);
		char[] mod = new char[10];
		mod = module.toCharArray();
		char c = Character.toUpperCase(mod[0]);
		// String C=new String(c);
		Character C = new Character(c);
		// C.toString();
		modul.replace(0, 1, C.toString());
		modul.append("s");
		System.out.println(modul.toString());

	}

	// @Test
	public void apitest() throws IOException {
		final String SCOPE = "https://www.googleapis.com/auth/urlshortener";
		final String CALLBACK_URL = "http://localhost:8080";
		final HttpTransport TRANSPORT = new NetHttpTransport();
		final JsonFactory JSON_FACTORY = new JacksonFactory();

		// FILL THESE IN WITH YOUR VALUES FROM THE API CONSOLE
		final String CLIENT_ID = "467942404347.apps.googleusercontent.com";
		final String CLIENT_SECRET = "3t4WpdNAJMzuPjaJw9dLzDdi";
		// Generate the URL to which we will direct users
		// String authorizeUrl = new GoogleAuthorizationRequestUrl(CLIENT_ID,
		// CALLBACK_URL, SCOPE).build();
		// System.out.println("Paste this url in your browser: " +
		// authorizeUrl);

		// Wait for the authorization code
		System.out.println("Type the code you received here: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String authorizationCode = in.readLine();

	}

	// @Test
	public void accessTokenTest() throws IOException {
		try {
			GoogleTokenResponse response = new GoogleAuthorizationCodeTokenRequest(
					new NetHttpTransport(),
					new JacksonFactory(),
					"467942404347.apps.googleusercontent.com",
					"3t4WpdNAJMzuPjaJw9dLzDdi",
					"4/WLwzUmp72CZe0-WK3_DRmkO6pSq3.0q4jzB25eMYWXE-sT2ZLcbSxoCTAdQI",
					"http://localhost:8080").execute();
			System.out.println("Access token: " + response.getAccessToken());
		} catch (TokenResponseException e) {
			if (e.getDetails() != null) {
				System.err.println("Error: " + e.getDetails().getError());
				if (e.getDetails().getErrorDescription() != null) {
					System.err.println(e.getDetails().getErrorDescription());
				}
				if (e.getDetails().getErrorUri() != null) {
					System.err.println(e.getDetails().getErrorUri());
				}
			} else {
				System.err.println(e.getMessage());
			}
		}
	}

	@Test
	public void unauthTest() throws IOException, ServiceException {

		// URL feedUrl =

		// "https://www.google.com/m8/feeds/contacts/raja.pandiya@megam.co.in/full";

		// System.out.println("URL: "+
		// Thread.currentThread().getContextClassLoader().getResource("com/google/common/collect/ImmutableSet.class"));
		final GoogleClientRequestInitializer KEY_INITIALIZER = new CommonGoogleClientRequestInitializer(
				"AIzaSyCUMB0QOnAHXowvK6x5D-ekHBR6OE1jCxY");

		/*
		 * JsonHttpRequestInitializer initializer = new GoogleKeyInitializer(
		 * "AIzaSyCUMB0QOnAHXowvK6x5D-ekHBR6OE1jCxY"); Plus plus =
		 * Plus.builder(new NetHttpTransport(), new JacksonFactory())
		 * .setApplicationName("megam-RESTAPI/3.0")
		 * .setJsonHttpRequestInitializer(initializer).build();
		 */

		Drive drive = new Drive.Builder(new NetHttpTransport(),
				new JacksonFactory(), null).setApplicationName("RESTAPI")
				.setGoogleClientRequestInitializer(KEY_INITIALIZER).build();

		/*
		 * File body = new File(); body.setTitle("My document");
		 * body.setDescription("A test document");
		 * body.setMimeType("text/plain");
		 * 
		 * java.io.File fileContent = new java.io.File(
		 * "/home/pandiyaraja/code/megam/development/deccanplato/src/test/resources/document"
		 * ); FileContent mediaContent = new FileContent("text/plain",
		 * fileContent);
		 * 
		 * File file = drive.files().insert(body, mediaContent).execute();
		 * System.out.println("File ID: " + file.getId());
		 */

		boolean useDirectUpload = false;
		String UPLOAD_FILE_PATH = "/home/pandiyaraja/code/megam/development/deccanplato/src/test/resources/document";
		java.io.File UPLOAD_FILE = new java.io.File(UPLOAD_FILE_PATH);
		if (UPLOAD_FILE.exists()) {
			System.out.println("file upload");
		}
		File fileMetadata = new File();
		fileMetadata.setTitle(UPLOAD_FILE.getName());
		System.out.println(UPLOAD_FILE.getName());

		InputStreamContent mediaContent1 = new InputStreamContent("text/plain",
				new BufferedInputStream(new FileInputStream(UPLOAD_FILE)));
		mediaContent1.setLength(UPLOAD_FILE.length());
		System.out.println(UPLOAD_FILE.length());
		Drive.Files.Insert insert = drive.files().insert(fileMetadata,
				mediaContent1);
		// MediaHttpUploader uploader = insert.getMediaHttpUploader();
		// uploader.setDirectUploadEnabled(useDirectUpload);
		// uploader.setProgressListener(MediaHttpUploaderProgressListener
		// progresslistner);
		insert.execute();

		/*
		 * URL feedURL =new URL("https://www.googleapis.com/drive/v2/files");
		 * List<File> result = new ArrayList<File>(); Drive.Apps.List request
		 * =drive.apps().list();
		 * System.out.println("FILE>LIST"+request.getKey());
		 */

		System.out.println(drive.toString());
		/*
		 * ContactsService contactService = null; try { contactService = new
		 * ContactsService("megam-RESTAPI-3"); String token =
		 * contactService.getAuthToken( "raja.pandiya@megam.co.in",
		 * "team4megam", "webservice", null, null, "RESTAPI");
		 * System.out.println(token); } catch (Exception e) {
		 * System.out.println(e); }
		 * 
		 * /* ContactsService myService = new ContactsService(null);
		 * 
		 * JsonHttpRequestInitializer initializer = new
		 * GoogleKeyInitializer("AIzaSyCUMB0QOnAHXowvK6x5D-ekHBR6OE1jCxY");
		 * 
		 * GoogleCredential credential = new GoogleCredential().setAccessToken(
		 * "AIzaSyCUMB0QOnAHXowvK6x5D-ekHBR6OE1jCxY");
		 * 
		 * //ContactsService myService = new
		 * ContactsService("<var>API Project</var>");
		 * 
		 * URL feedUrl = new URL(
		 * "https://www.google.com/m8/feeds/contacts/raja.pandiya@megam.co.in/full"
		 * ); ContactFeed resultFeed = contactService.getFeed(feedUrl,
		 * ContactFeed.class);
		 * System.out.println(resultFeed.getTitle().getPlainText()); for
		 * (ContactEntry entry : resultFeed.getEntries()) {
		 * 
		 * System.out.println("ENTRY" + entry.getName()); }
		 */

	}
}
