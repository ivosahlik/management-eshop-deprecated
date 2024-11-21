
mvn clean package -Dspring.profiles.active=dev -Dmaven.test.skip=true
cd target
java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 adminportal-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --debug
