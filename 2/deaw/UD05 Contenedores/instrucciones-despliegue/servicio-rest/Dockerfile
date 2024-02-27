FROM openjdk:21
EXPOSE 8080
ARG WAR_FILE=venteo-api.war
ADD ${WAR_FILE} app.war
ENTRYPOINT ["java", "-war", "app.war"]
