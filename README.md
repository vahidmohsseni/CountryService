# Country Information DB
### A JAVA based Implementation of a RESTful API using SpringBoot Reactor Core and R2DBC


## How it works?
In this project, all dependency are added based on `maven`. To the date of adding to this `commit` all of them are
the latest version. Let's get started!

There are a couple of easy ways to run this project like all other JAVA applications. However, 
here, two simple commands are announced.

```shell
git clone https://github.com/vahidmohsseni/CountryService
cd CountryService
```

### Way 1. With Docker
```shell
./mvnw package
docker-compose up
```

### Way 2. With `MAVEN`

```shell
./mvnw spring-boot::run
```

### Way 3. With `MAVEN`
```shell
./mvnw package
java -jar target/MyApp-0.0.1-SNAPSHOT.jar 
```


The application will run on `port` `8080`. [http://localhost:8080/countries/](http://localhost:8080/countries/)


## Requirements
Remember to install JAVA JDK version 8 or upper.
If you want to run it using Docker, the latest version of docker is fine.


## APIs Description
In this application there are two type of APIs.
#### `/countries/ext/{name}`
First type uses an external service to fetch the information.
This API is available in the location `/countries/ext/{name}`. For example, 
[http://localhost:8080/countries/ext/finland](http://localhost:8080/countries/ext/finland)
will show you following result:
```shell
curl localhost:8080/countries/ext/finland

{"name":"Finland","country_code":"FI","capital":"Helsinki","population":5530719,"flag_file_url":"https://flagcdn.com/w320/fi.png"}
```

#### `/countries/` and `/countries/{name}`
The second type, reads the information from a local database. For example,
[http://localhost:8080/countries/](http://localhost:8080/countries/)
will result like the following:
```shell
curl localhost:8080/countries/

[{"name":"Sudan","country_code":"SD"},{"name":"Mexico","country_code":"MX"},{"name":"Cuba","country_code":"CU"},{"name":"Finland","country_code":"FI"} ... ]
```
