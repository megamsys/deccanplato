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
package org.megam.deccanplato.provider.googleapp.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.googleapp.Constants.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

/**
 * 
 * @author pandiyaraja
 * 
 * This class implements the business activity of GoogleApp Drive method.
 * this class is implemented by using Google API Unauthenticate type(Using API Key), and this class needs 
 * client API-KEY and application name to get authenticate 
 * this class has two methods, to implement business functions, like upload, download,
 */
public class DriveImpl implements BusinessActivity{

	private Drive drive;
	private File uploadedFile;
	private BusinessActivityInfo bizInfo;
	private Map<String, String> args = new HashMap<String, String>();
	/**
	 * this method initialize the operations to perform (like upload and download a file)
	 * authentication set in this method by calling Drive class's build method
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.bizInfo=tempBizInfo;
		this.args=tempArgs;
		final GoogleClientRequestInitializer KEY_INITIALIZER = new CommonGoogleClientRequestInitializer(
				args.get(API_KEY));
		 drive = new Drive.Builder(new NetHttpTransport(),
				new JacksonFactory(), null).setApplicationName(args.get(APPLICATION_NAME))
				.setGoogleClientRequestInitializer(KEY_INITIALIZER).build();
		
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#run()
	 */
	@Override
	public Map<String, String> run() {

		Map<String, String> outMap = new HashMap<String, String>();
		switch (bizInfo.getActivityFunction()) {
		case CREATE:
			outMap = upload(outMap);
			break;
		case LIST:
			outMap = list(outMap);
			break;
		case UPDATE:
			outMap = download(outMap);
			break;
		case DELETE:
			outMap = delete(outMap);
			break;
		default:
			break;
		}

		return outMap;
	}

	/**
	 * this method uploads a file in google Drive app
	 * args map has all the details to upload a file
	 * @param outMap
	 * @return
	 * 
	 */
	private Map<String, String> upload(Map<String, String> outMap) {
		
		boolean useDirectUpload = true;
		String UPLOAD_FILE_PATH = args.get(UPLOAD_PATH);
		java.io.File UPLOAD_FILE = new java.io.File(UPLOAD_FILE_PATH);
		File fileMetadata = new File();
		fileMetadata.setTitle(UPLOAD_FILE.getName());
		

		InputStreamContent mediaContent1 = null;
		try {
			mediaContent1 = new InputStreamContent(args.get(FILE_TYPE),
					new BufferedInputStream(new FileInputStream(UPLOAD_FILE)));
			mediaContent1.setLength(UPLOAD_FILE.length());
			Drive.Files.Insert insert;
			try {
				insert = drive.files().insert(fileMetadata,
						mediaContent1);
				MediaHttpUploader uploader = insert.getMediaHttpUploader();
				 uploader.setDirectUploadEnabled(useDirectUpload);
				// uploader.setProgressListener(MediaHttpUploaderProgressListener
				// progresslistner);
				uploadedFile=insert.execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> list(Map<String, String> outMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * this method downloads a file in google Drive app
	 * args map has all the details to download a file
	 * @param outMap
	 * @return
	 * 
	 */
	private Map<String, String> download(Map<String, String> outMap) {
		
		boolean useDirectDownload=true;
		String DIR_FOR_DOWNLOADS = args.get(DOWNLOAD_PATH);
		java.io.File parentDir = new java.io.File(DIR_FOR_DOWNLOADS);
	    if (!parentDir.exists() && !parentDir.mkdirs()) {
	      try {
			throw new IOException("Unable to create parent directory");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    OutputStream out = null;
		try {
			out = new FileOutputStream(new java.io.File(parentDir, uploadedFile.getTitle()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    Drive.Files.Get get;
		try {
			get = drive.files().get(uploadedFile.getId());
			 MediaHttpDownloader downloader = get.getMediaHttpDownloader();
			    downloader.setDirectDownloadEnabled(useDirectDownload);
			    //downloader.setProgressListener(new MediaHttpDownloaderProgressListener());
			    downloader.download(new GenericUrl(uploadedFile.getDownloadUrl()), out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   

		return null;
	}

	/**
	 * @param outMap
	 * @return
	 */
	private Map<String, String> delete(Map<String, String> outMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "drive";
	}

	
}
