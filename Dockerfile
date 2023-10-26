FROM openjdk
VOLUME /tmp
RUN mkdir /work
COPY . /work
WORKDIR /work
RUN microdnf install findutils
RUN /work/gradlew build
EXPOSE 8080
CMD ["java","-jar","/work/build/libs/art-gallery-0.0.1-SNAPSHOT.jar"]