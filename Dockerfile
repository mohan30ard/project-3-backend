FROM openjdk:8

ADD target/revature-railways-backend.jar revature-railways-backend.jar

EXPOSE 9848

ENTRYPOINT ["java","-jar","revature-railways-backend.jar"]