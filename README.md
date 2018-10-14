# Offersmanager
A RESTful facade backend API for managing product offers.


## Toolset

- Spring Boot
- Spring MVC
- Spring Data JPA
  - out-of-the-box DAO-generation at runtime via method-naming conventions

- Hibernate
- Jackson Annotations
  - custom serialization of references (@JsonIdentityInfo, @JsonIdentityReference)
- H2 Database

- Maven
- Git
- Mockito

## Requirements

- product are grouped in different categories (books, bikes, food)
- product offers can be added in single mode or grouped in an offer 
- endpoint for providing  views of  products details with relative offer if associated (in JSON)
- endpoint for providing  views of  offers details with relative products associated (in JSON)
- endpoint for creating products 
- endpoint for creating offers
- endpoint for saveing a prodct associated to an offer
- endpoint for deleting a product
- endpoint for deleting an offer

- methods for setting an expiring date  
  - management of product expired
  
  
## Unit and Integration Testing

- mocking
- service unit testing
- repository integration testing with embedded in-memory database accessed through Spring JPA TestEntityManager
- controller integration testing using MockMvc instance to setup a Spring MVC context with a web server

## Quick start
Below all the commands to clone, build and run the project with Maven and Java 8 JDK:

- git clone https://github.com/geoDwarf/offersmanager
- cd MoviesRestService
- mvn  clean install
- java -jar target/com-ubs-ws-movies-web-1.0.jar
- the embedded servlet container starts at http://localhost:8080

## How to test step by step via REST client 

########  GET PRODUCTS  ################


GET - http://localhost:8080/offers/getProduct/2

GET - http://localhost:8080/offers/getProduct/3

GET - http://localhost:8080/offers/getProduct/4

GET - http://localhost:8080/offers/getProduct/5

GET - http://localhost:8080/offers/getProduct/6

GET - http://localhost:8080/offers/getProduct/7

GET - http://localhost:8080/offers/getProduct/8

GET - http://localhost:8080/offers/getProduct/9

Response example
{
    "productId": 2,
    "offerExpiringDate": "2019-08-27",
    "offerStartingDate": "2019-08-21",
    "offerPrice": 150.12,
    "offerDescription": "An unique chance of getting a first class bike for rediculous price ",
    "productName": "Cento10pro",
    "daysValidityPeriod": 6,
    "brand": "Wilier",
    "speeds": 12,
    "frontForkBrand": null,
    "rearForkBrand": null,
    "fullSuspended": false,
    "expired": false
}

########## DELETE PRODUCT  ############# 

DELETE - http://localhost:8080/offers/deleteProduct/7

########## CHECK DELETE   #############

GET http://localhost:8080/offers/getProduct/7

########   CHECK DUPLICATE ##############

POST http://localhost:8080/offers/savePizza

{
    "productId": 6,
    "offerExpiringDate": "2019-08-25",
    "offerStartingDate": "2019-08-17",
    "offerPrice": 15.3,
    "offerDescription": "The most classical italian pizza served at special price",
    "productName": "Pizza Margherita",
    "daysValidityPeriod": 8,
    "origin": "Piedmont",
    "calories": 558.3,
    "expiringDate": null,
    "dressing": "Margherita",
    "expired": false
}

############  SAVE NEW PRODUCT ######################

POST  http://localhost:8080/offers/savePizza

{
    "productId": 12,
    "offerExpiringDate": "2019-08-25",
    "offerStartingDate": "2019-08-17",
    "offerPrice": 15.3,
    "offerDescription": "The most classical italian pizza served at special price",
    "productName": "Pizza Napoli",
    "daysValidityPeriod": 8,
    "origin": "Piedmont",
    "calories": 558.3,
    "expiringDate": null,
    "dressing": "Napoli",
    "expired": false
}

############ NOT FOUND #####################

GET http://localhost:8080/offers/getProduct/112

########### MISSING MANDATORY PARAMETERS ###  

POST - http://localhost:8080/offers/saveMountainBike

{
    "productId": 2,
    "offerExpiringDate": "2019-08-27",
    "offerStartingDate": null,
    "offerPrice": 150.12,
    "offerDescription": "An unique chance of getting a first class bike for rediculous price ",
    "productName": "Cento10pro",
    "daysValidityPeriod": 6,
    "brand": "Wilier",
    "speeds": 12,
    "frontForkBrand": "FOX",
    "rearForkBrand": null,
    "fullSuspended": false,
    "expired": false
}


############# SAVE AN EXPIRED PRODUCT ################


POST  http://localhost:8080/offers/savePizza

{
    "productId": 12,
    "offerExpiringDate": null,
    "offerStartingDate": "2018-08-17",
    "offerPrice": 15.3,
    "offerDescription": "The most classical italian pizza served at special price",
    "productName": "Pizza Napoli",
    "daysValidityPeriod": 8,
    "origin": "Piedmont",
    "calories": 558.3,
    "expiringDate": null,
    "dressing": "Napoli",
    "expired": false
}

In a prduction environment saving a product with an expiring date in the past should not be possible.
The application is ready for such a scenario but for testing porpuses at the moment the control has been disabled.


 ############ CHECK THE UNAVAILABILITY OF THE EXPIRED PRODUCT #####

GET http://localhost:8080/offers/getProduct/12


#############  CHECK AN EXISTING OFFER ############################

GET http://localhost:8080/offers/getOffer/15

########### SAVE AN OFFER #############################

POST http://localhost:8080/offers/saveOffer

{
    "offerId": 55,
    "products": []
}

########## SAVE  PRODUCTS TO A SPECIFIC OFFER #########

POST http://localhost:8080/offers/savePizza


{
    "productId": 58,
    "offerExpiringDate": "2019-08-25",
    "offerStartingDate": "2019-08-17",
    "offerPrice": 15.3,
    "offerDescription": "The most classical italian pizza served at special price",
    "productName": "Pizza Napoli",
    "daysValidityPeriod": 8,
    "origin": "Piedmont",
    "calories": 558.3,
    "expiringDate": null,
    "dressing": "Capricciosa",
    "expired": false,
    "offer" : { "offerId":55, "products":[]}
    
}

POST http://localhost:8080/offers/saveMountainBike

{
    "productId": 215,
    "offerExpiringDate": "2019-08-26",
    "offerStartingDate": "2019-08-21",
    "offerPrice": 2255,
    "offerDescription": "A cheap bike",
    "productName": "Mountain Bike Radon",
    "daysValidityPeriod": 5,
    "offer": { "offerId":55, "products":[]},
    "brand": "Radon bike",
    "speeds": 22,
    "frontForkBrand": "FOX",
    "rearForkBrand": "FOX",
    "fullSuspended": true,
    "expired": false
}

############ CHECK THAT PRODUCTS HAVE BEEN ADDED TO THE OFFER #######


GET http://localhost:8080/offers/getOffer/55


############ DELETE OFFER ########################################ù

DELETE http://localhost:8080/offers/deleteOffer/11

