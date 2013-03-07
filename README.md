# deccanplato 
[![Build Status](https://travis-ci.org/indykish/deccanplato.png)(https://travis-ci.org/indykish/deccanplato)

Named after the [largest plateau in Southern India](http://en.wikipedia.org/wiki/Deccan_Plateau)

`deccanplato` is an opensouce cloud integation REST based connectors for cloud apps 
* CRM     		  - [Salesforce](http://developer.force.com) 
* CRM     		  - [ZohoCRM](http://www.zoho.com/crm/help/api/)
* Invoice 		  - [ZohoInvoice](http://www.zoho.com/invoice/api/index.html)
* CRM     		  - [SugarCRM](http://developers.sugarcrm.com/) 
* Team & Collab   - [Google App](https://developers.google.com/google-apps/)
* Inv. & Acct.	  - [Xero](http://blog.xero.com/developer/api-overview/) 
* Cloud Storage   - [Box.com](http://developers.box.com/) 
* Cloud Storage   - [Dropbox](https://www.dropbox.com/developers/core/api)
* Cloud Comm      - [Twilio](http://www.twilio.com/doers)
* Lang. Interpret - [Maluuba](http://dev.maluuba.com/) 

...More to come.

used to bring `open source cloud integration` using a `cloud identity` making a live cloud. 

## `live cloud ` ? 

When x happens in  cloud app, y gets a notification. 

### Usecase can be anything : 
* When a product is shipped from an inventory system, 
* Generate Invoice
* Update the CRM records, 
* Send a voice notification to the customer saying that it was shipped 
* Send out an email


This uses Java 1.7, REST, Spring and the RESTful cloud app interfaces.

### How will this happen
Watch our [blog.megam.co](http://blog.megam.co) for updates
Slideshare.net [slideshare.net](http://slideshare.net/indykish) for updates/ 

We'll launch soon in `Q1 2013` 

Stay tuned : [!Live cloud integration using Cloud identity](http://megam.co)

### Requirements

Java 1.7+
Tomcat 7
(or) [Hosted integration for free](http://megam.co)  


## Running the application locally

Build with:

    $ mvn clean install

Then run in tomcat7 with:

    $ mvn tomcat7:deploy

## Running on your tomcat7

Clone this project locally:

    $ git clone https://github.com/indykish/deccanplato.git
    
    $ mvn tomcat7:deploy
    
Getting Started : [Opensource cloud integration :](http://megam.co/getting_started.html)


## Running on your tomcat7

Clone this project locally:

Create a new app on Heroku (make sure you have the [Heroku Toolbelt](http://toolbelt.heroku.com) installed):

    $ heroku login
    $ heroku create -s cedar

Upload the app to Heroku:

    $ git push heroku master

Open the app in your browser:

    $ heroku open

Getting Started : [Opensource cloud integration :](http://megam.co/getting_started.html)
