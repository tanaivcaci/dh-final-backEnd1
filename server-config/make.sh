mvn clean && mvn package -DskipTests
docker build . -t server-config
