FROM adoptopenjdk/openjdk11:jre-11.0.4_11-ubuntu

ADD build/libs/transactionsAPI.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8080


