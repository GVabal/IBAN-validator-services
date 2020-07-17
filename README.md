# IBAN-Validator-Services

Run with Docker: `docker run -p {your port}:8080 gvabal/iban-validator-service`

Run with Maven: `./mvnw package && java -jar target/*.jar`

#### Endpoints (assuming the application is running locally on port 80):

##### SOAP example

`curl --header "content-type: text/xml" -d @example-request.xml http://localhost/soap`

* takes request in .xml form for one IBAN and returns processed result

example-request.xml:

![example](https://user-images.githubusercontent.com/49102436/87785266-9286ee80-c840-11ea-801a-b734c2e00c80.jpg)

response:

![example2](https://user-images.githubusercontent.com/49102436/87785706-4e481e00-c841-11ea-9d7b-f6cfb2e0aaee.jpg)

##### REST example

`curl -X POST -H "Content-Type: application/json" -d '{"numbers":["GL8964710123456700","GL89647101234","GL8964710123456789"]}' http://localhost/rest`

* takes array of IBANs in json body and returns processed results all at once

response:

![example3](https://user-images.githubusercontent.com/49102436/87786291-5d7b9b80-c842-11ea-88c3-4a84954a0881.jpg)

