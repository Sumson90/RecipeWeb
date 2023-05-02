FROM maven:3.6.3-openjdk-17-slim AS MAVEN_BUILD
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package


FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
COPY target/MyFavoriteRecipe-*.jar /app.jar
ENV APP_STORAGE_LOCATION=./uploads/
RUN mkdir -p ./uploads
RUN mkdir -p ./uploads/img
RUN mkdir -p ./uploads/files
COPY --from=MAVEN_BUILD /target/rest-offers-*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

