FROM eclipse-temurin:17.0.6_10-jre

ENV SERVER_PORT=8080
ENV AUTHOR_SERVICE_URL=http://localhost:8082
ENV BOOK_SERVICE_URL=http://localhost:8081

EXPOSE 8080

COPY target/demo-0.0.1-SNAPSHOT.jar /opt/gateway-service/gateway-service.jar

CMD ["java", "-jar", "/opt/gateway-service/gateway-service.jar"]
