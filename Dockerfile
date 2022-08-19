FROM openjdk:11
EXPOSE 8081
ADD target/component_processing_docker.jar component_processing_docker.jar
ENTRYPOINT ["java","-jar","/component_processing_docker.jar"]