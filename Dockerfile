FROM eclipse-temurin:17
WORKDIR /app
COPY target/spring-course-Management-0.0.1-SNAPSHOT.war app.war
CMD ["java","-jar","app.war"]
