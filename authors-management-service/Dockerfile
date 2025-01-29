FROM eclipse-temurin:17-jdk

ENV SERVER_PORT=8082

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=password


ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV SPRING_H2_CONSOLE_enabled=true

ENV RPG_CHARACTER_URL=http://localhost:8081

EXPOSE 8082

COPY /target/demo-0.0.1-SNAPSHOT.jar /opt/authors-management/authors-management.jar

CMD ["java", "-jar", "/opt/authors-management/authors-management.jar"]

