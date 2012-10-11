deccanplato

REST cloud connectors for the cloud providers Salesforce, Zoho, SugarCRM, Google App, Box.com, Dropbox... More to come.

## Running the application locally

Build with:

    $ mvn clean install

Then run with:

    $ java -jar target/dependency/webapp-runner.jar target/*.war

## Running on Heroku

Clone this project locally:

    $ git clone https://github.com/metadaddy-sfdc/spring-mvc-fulfillment-base.git

Create a new app on Heroku (make sure you have the [Heroku Toolbelt](http://toolbelt.heroku.com) installed):

    $ heroku login
    $ heroku create -s cedar

Upload the app to Heroku:

    $ git push heroku master

Open the app in your browser:

    $ heroku open
