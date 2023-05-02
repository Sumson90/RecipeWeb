FROM maven:3.6.3-openjdk-17-slim AS MAVEN_BUILD
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package


FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
ENV APP_STORAGE_LOCATION=./uploads/
RUN mkdir -p ./uploads
RUN mkdir -p ./uploads/img
RUN mkdir -p ./uploads/files
COPY --from=MAVEN_BUILD /target/MyFavoriteRecipe-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-Dserver.address=89.117.37.212", "-jar", "/app.jar"]

