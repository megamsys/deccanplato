# deccanplato 
![Build Status](https://travis-ci.org/indykish/deccanplato.png)(https://travis-ci.org/indykish/deccanplato)

`deccanplato` - Named after the [largest plateau in Southern India](http://en.wikipedia.org/wiki/Deccan_Plateau)

> `deccanplato` is an opensouce cloud integation REST based connectors for cloud apps 
used to bring `open source cloud integration` using a `cloud identity` making a live cloud. 

> * CRM     		  - [Salesforce](http://developer.force.com) 
> * CRM     		  - [ZohoCRM](http://www.zoho.com/crm/help/api/)
> * Invoice 		  - [ZohoInvoice](http://www.zoho.com/invoice/api/index.html)
> * CRM     		  - [SugarCRM](http://developers.sugarcrm.com/) 
> * Team & Collab   - [Google App](https://developers.google.com/google-apps/)
> * Inv. & Acct.	  - [Xero][xero]
> * Cloud Storage   - [Box.com](http://developers.box.com/) 
> * Cloud Storage   - [Dropbox](https://www.dropbox.com/developers/core/api)
> * Cloud Comm      - [Twilio](http://www.twilio.com/doers)
> * Lang. Interpret - [Maluuba](http://dev.maluuba.com/) 
...More to come.


# Live cloud  ? 

When `<x>` happens in  a cloud app, `<y>` gets a notification and does something `<z>`. 
This takes cloud integration to the next level.  

where `<x>`, `<y>` are cloud apps. `<z>` is an action to perform.

## Usecase - Actionable Evented Cloud Apps : 

The `system` mentioned below are cloud apps

* When a product is shipped from an inventory system, 
* Generate Invoice
* Update the CRM records, 
* Send a voice notification to the customer saying that it was shipped 
* Send out an email

This uses OpenJDK 1.7, Spring 3.2.2, REST, JSON and the RESTful cloud app interfaces.

## How will this happen

Watch our [blog.megam.co][1] for updates
Slideshare.net [slideshare.net/indykish][2] for updates

We'll launch soon in `Q2 2013` 

Stay tuned : [Live cloud integration using Cloud identity][4]
Blog : [Cloud Realtime Streaming - Part 1][5]
Blog : [Cloud Realtime Streaming - Live Cloud][6]

### Requirements

OpenJDK 1.7+ [Install Instructions](http://openjdk.java.net/install/)

Tomcat 7 [Install Instructions](http://tomcat.apache.org/download-70.cgi)

make sure the file `<tomcat_install_home>\conf\tomcat_users.xml` has an `user=admin, pw=admin`

```xml
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<role rolename="manager"/>
<role rolename="admin-gui"/>
<role rolename="admin-script"/>
<role rolename="admin"/>
<user username="admin" password="admin" roles="manager-gui,admin-gui,manager,admin,manager-script,admin-script"/>
```
* Tested on Ubuntu 12.04 or above
 
* `optional`-[Hosted cloud integration/cloud identity for free][4] `launching soon`

## Running the application

Clone this project locally:

    $ git clone https://github.com/indykish/deccanplato.git

Build with:

    $ mvn clean verify

Then run in tomcat7 with:

    $ mvn tomcat7:deploy

## Testing your install

Start the tomcat7 container on port 8080 with:

    $ ~/tomcat/bin/start.sh

### Let us create an account with salesforce.

In your src/test/resource/salesforcecrm, there exists a json named : account_create.json

	system
		access
		Ignore the fields `project_id`	`access_org_id`		`access_account_name`. 
		These are internal fields that are used for tracking a project based on an id.
				
	provider
		access : Feed the appropriate data.
	The import aspect to note here is the provider shall be `salesforcecrm` 
	and `bizactivity` shall be `account#create`	

For further details, read the documentation at [Opensource cloud integration :][3] * launching shortly

```json
{
	"system": {
		"access": {
			"project_id": "3c78f781-fc28-4c69-8fad-0ca66f2c5dbc",
			"api_token": "oVj29MbD2LcXzsRzNFx9vw==",
			"access_email": "alrin@megam.com",
			"access_org_id": 1,
			"access_account_name": "MegamSyste"
		}
	},
	"provider": {
		"access": {
			"consumer_key": "3MVG9Y6d_Btp4xp51yG_eZBS13fsAsN55a0mb4tjHy0V1jx4sOOo_7.HtHfu0dUNZ9qaIF8mqWpmUtWVbDfZo",
			"consumer_secret": "2416933755273187085",
			"access_username": "pontiyaraja@gmail.com",
			"access_password": "pandiya1988hKIZMcZwbLX0vSRHY9ddjuYHQ",
			"provider": "salesforcecrm",
			"category": "CRM",
			"description": "My first Connector Project",
			"user_email": "alrin@megam.com",
			"org_name": "Megam Systems"
		},
		"business_activity": {
			"biz_function": "account#create",
                        "name":"Raja"
		}
	},
	"execution": {
		"output": {
			"type": "default",
			"location": "default"
		}
	}
}
```

Use the class available in `src\test\java`, to perform a JUnit test. 

```java
public class SaleforceCRMAdapterTest {

	private static final String SALESFORCE="salesforcecrm";
    @Test
	public void salesforceTest() { 
    	
    	GenericApplicationContext ctx = new GenericApplicationContext();
    	XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
    	xmlReader.loadBeanDefinitions(new ClassPathResource(
    			"applicationContext.xml"));
    	ctx.refresh();
    	ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");
    	 
		List<String> busiMethod =new ArrayList<String>();
		busiMethod.add("account");		
		List<String> busiActivity = new ArrayList<String>();
		busiActivity.add("create");
		
		for(String function: busiMethod) {
			for(String activity: busiActivity) {
				CommonTest ctest=new CommonTest();
				RequestData reqData;
				reqData=ctest.commonTest(function, activity, SALESFORCE);
				if(function.equalsIgnoreCase("user") && activity.equalsIgnoreCase("create")) {
				testAdapterAccess(reqData);
				}
				ctest.testBusinessImpl();
			}
		}					
	}
    private void testAdapterAccess(RequestData reqData)  {

		SalesforceCRMAdapterAccess saa = new SalesforceCRMAdapterAccess();
		try {
			DataMap dmap = saa.authenticate(reqData.getGeneral());
		} catch (AdapterAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}   
}
```

    
    Post execution verify that an account indeed exists in salesforce.
    
Getting Started : [Opensource cloud integration :][3]


## Running on heroku

Clone this project locally.

Create a new app on Heroku (make sure you have the [Heroku Toolbelt](http://toolbelt.heroku.com) installed):

    $ heroku login
    $ heroku create -s cedar

Upload the app to Heroku:

    $ git push heroku master

Open the app in your browser:

    $ heroku open


## Want to contribute ? 

Go ahead and setup a development environment

### Setup the prereqs 

OpenJDK 1.7, Tomcat 7, Maven - 3.0.5, Ubuntu 12.10 > preferred, Eclipse Juno >

### Dependency *optional 

> This step is `only needed` if you changed [xero public application](https://github.com/indykish/xero)

> Has a dependency on [xero public application](https://github.com/indykish/xero.git). 

The cloned copy contains a local maven repo(deccanplato/repo) with the required xero jar files. So this already taken care.

### Xero changed ? 

Clone the [xero](https://github.com/indykish/xero.git) project locally.

#### Generating an updated xero*.jar.

From your xero project, run

	$ mvn package

After your run it,  a `new jar of xero`, will automatically land in your `deccanplato/lib` directory.

We assume that both xero and deccanplato projects are in the same eclipse workspace. 

	Download this [python script](https://s3-ap-southeast-1.amazonaws.com/megam/public/mvnlocalrepo.py) and place it in your ~/bin directory. 

Add your `~/bin` into your `PATH` variable in `.bashrc` (If you are on Ubuntu)

Run the following from deccanplato directory 

``` 
$ ~/deccanplato$ mvnlocalrepo.py -i
-----
Processing `lib/com.rossjourdain.xero.xeroapi_1.2.jar`
Choose a correct artifactId for `com.rossjourdain.xero.xeroapi`:
1) xeroapi
2) xero.xeroapi
3) rossjourdain.xero.xeroapi

1

A successful run will provide you the following.

<dependency>
  <groupId>com.rossjourdain.xero</groupId>
  <artifactId>xeroapi</artifactId>
  <version>1.2</version>
</dependency>

```

Compile deccanplato.

	mvn compile


Getting Started : [Opensource cloud integration :][3] *launching shortly

[1]:http://blog.megam.co
[2]:https://slideshare.net/indykish
[3]:http://megam.co/getting_started.html
[4]:http://www.megam.co
[5]:http://blog.megam.co/archives/381
[6]:http://blog.megam.co/archives/626
[xerogit]:https://github.com/indykish/xero
[xero]:http://blog.xero.com/developer/api-overview/

#### TO - DO

* Interface to [tap](https://github.com/indykish/tap.git)
* Consume customers cloud identity 
* Metrics
	
# License


|                      |                                          |
|:---------------------|:-----------------------------------------|
| **Author:**          | KishorekumarNeelamegam (<nkishore@megam.co.in>)
|					   | R Pandiyaraja (<raja.pandiya@yahoo.com>)	
| **Copyright:**       | Copyright (c) 2012-2013 Megam Systems.
| **License:**         | Apache License, Version 2.0

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


