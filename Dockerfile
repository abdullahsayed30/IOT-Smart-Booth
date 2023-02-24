FROM amazoncorretto:17

MAINTAINER "Abdullah Sayed Ahmed Sallam"

EXPOSE 5155

COPY build/libs/*.jar iot-booth.jar

ENTRYPOINT ["java", "-jar", "/iot-booth.jar"]