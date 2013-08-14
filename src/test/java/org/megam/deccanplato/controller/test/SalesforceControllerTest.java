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
package org.megam.deccanplato.controller.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.junit.Test;

/**
 * @author pandiyaraja
 * 
 */
public class SalesforceControllerTest {
	@Test
	public void accountTest() {

		RestClient rc = new RestClient();

		StringBuilder strb = new StringBuilder();
		BufferedReader br = null;
		String inputJsonPath;
		try {
			inputJsonPath = new File(".").getCanonicalPath()
					+ java.io.File.separator + "src" + java.io.File.separator
					+ "test" + java.io.File.separator + "resources"
					+ java.io.File.separator + "salesforcecrm"
					+ java.io.File.separator + "account_create.json";
			br = new BufferedReader(new FileReader(inputJsonPath));

			String currentLine = "";
			while ((currentLine = br.readLine()) != null) {
				strb.append(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Resource resource = rc
				.resource("http://localhost:8080/deccanplato/provider/crm");
		String response = resource.contentType("application/json")
				.accept("application/json").post(String.class, strb.toString());
		System.out.println("-----------"+response);
	}
}
