FROM devbudhathoki05/apitestimage-testdep
COPY src /home/apiframework/src
COPY pom.xml /home/apiframework/pom.xml
COPY testng.xml /home/apiframework/testng.xml
WORKDIR /home/apiframework
ENTRYPOINT ["mvn", "clean", "test"]
