FROM maven:3.6.0-jdk-8 as maven

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn package -DskipTests
ENV MYSQL_URL=localhost MYSQL_PORT=3306 MYSQL_USR=admin MYSQL_PW=admin

# our final base image
FROM openjdk:8-jre-alpine

# set deployment directory
WORKDIR /final-capstone

# copy over the built artifact from the maven image
COPY --from=maven target/User-Management-0.0.1-SNAPSHOT.jar ./

# set the startup command to run your binary
CMD ["java", "-jar", "./User-Management-0.0.1-SNAPSHOT.jar"]
