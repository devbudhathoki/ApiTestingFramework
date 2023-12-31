json-server --host 192.168.1.66 --port 4000 --watch db.json
http://192.168.1.66:4000/clients

docker build -t apitestimage:latest .
docker run -v /tmp/openai:/tmp/openai --name apitest apitestimage

docker pull devbudhathoki05/apitestimage-testdep
docker run -v /tmp/openai:/tmp/openai --name apitest devbudhathoki05/apitestimage-testdep

docker cp apitest:/home/apiframework .

docker commit containerID (to create new images)
docker tag imageID devbudhathoki05/apitestimage-testdep:latest

containers=$(docker container ls -a | grep api* | awk '{print $1}' | tr '\n' ' ') && docker container stop $containers && docker container rm $containers

images=$(docker images | grep api* | awk '{print $3}'|   tr '\n' ' ') && docker image rmi -f $images