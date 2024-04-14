FROM eclipse-temurin:17-jdk

COPY build/libs/bank-transaction-root-0.0.1-SNAPSHOT.jar neoris-account-ws-0.0.1-SNAPSHOT.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","neoris-account-ws-0.0.1-SNAPSHOT.jar"]