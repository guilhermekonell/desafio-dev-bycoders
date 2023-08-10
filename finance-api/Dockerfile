FROM maven:3.9.3-amazoncorretto-17

WORKDIR /app

COPY . /app

RUN mvn -Dmaven.test.skip=true clean package

CMD ["java", "-jar", "target/finance-api-0.0.1-SNAPSHOT.jar"]


