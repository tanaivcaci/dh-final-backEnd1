mvn clean && mvn package -DskipTests
docker build . -t movies-service
