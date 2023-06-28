# mongo-service

This is a **Java Spring Boot** practice project to mix and test knowledge from different sources. In this case, it is a RESP API to serve books from MongoDB in an imperative form.

To create the image from of the Java project using the Dockerfile and execute it, it can be used:
```shell
mvn clean install
docker image build -t mongo-service:0.1 .
docker container run --rm --network mongodb_default -p 8992:8992 --name mongo-service -e JAVA_TOOL_OPTIONS="-DMONGODB_HOST_IP=<MONGODB_HOST_IP>" mongo-service:0.1
```
<MONGODB_HOST_IP> is got from inspecting the MongoDB container.

**docker-compose.yaml** is used to initialize the environment:
```yaml
version: '3.8'
services:

  mongodb:
    image: mongo:6-jammy
    container_name: mongoDB
    ports:
      - '27017:27017'
    volumes:
      - mongodbvolume:/data/db

volumes:
  mongodbvolume:
    driver: local
```