FROM openjdk
VOLUME /tmp
RUN microdnf install findutils
RUN mkdir /work
WORKDIR /work
COPY . /work
RUN /work/gradlew build
EXPOSE 8080
CMD ["java","-jar","/work/build/libs/art-gallery-0.0.1-SNAPSHOT.jar", "--spring.datasource.url=jdbc:postgresql://${DB_SERVER}/art-gallery"]
