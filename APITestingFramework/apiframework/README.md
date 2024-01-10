# ApiFramework

* **Testng**: *Testing framework uses listener class and setting testsuites and parallel test run*
* **RestAssured** : *Testing CURD api operation*
* **Faker**: *To generate test data*
* **ExtentReport**: *Report generation*
* **Jackson-DataBind**: *Serializing and Deserializing json object*
* **AsserJ**: *Assertion test result*
* **Renovate**: *Dependency management*

## Prerequisite :
* Run json-server with your host ip address. Here host ip address is 192.168.1.90
```
json-server --host 192.168.1.90 --port 4000 --watch db.json
http://192.168.1.90:4000/clients
```
* Create /tmp/openai file with OPENAI_API_KEY as follows:
```
cat /tmp/openai
openAIKey=sk-xI2dsdrtdsds3NrT3BlbdrFzmA5YzO5LLIha6bWu
```

#### Run Test from project root where pom.xml file is present

```
mvn clean test
```



# Building docker image:
while building docker image mount volume from host to pass openai key as follows 
```
docker build -t apitestimage:latest .
docker run -v /tmp/openai:/tmp/openai --name apitest apitestimage

```
Building docker image from existing docker container:
```
docker commit containerID (to create new images)
docker tag imageID devbudhathoki05/apitestimage-testdep:latest
 ```

``` 
docker pull devbudhathoki05/apitestimage-testdep
docker run -v /tmp/openai:/tmp/openai --name apitest devbudhathoki05/apitestimage-testdep
```

## Copying artifcats from docker to host machine
``` 
docker cp apitest:/home/apiframework .
```

## removing docker constainers and images
``` 
containers=$(docker container ls -a | grep api* | awk '{print $1}' | tr '\n' ' ') && docker container stop $containers && docker container rm $containers

images=$(docker images | grep api* | awk '{print $3}'|   tr '\n' ' ') && docker image rmi -f $images
```