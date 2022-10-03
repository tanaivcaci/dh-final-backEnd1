mvn clean && mvn package -DskipTests
docker build . -t catalog-service
