FROM openjdk:17-oracle

VOLUME /tmp

WORKDIR app

ARG JAV_FILE=./build/libs/*.jar

COPY ${JAV_FILE} app.jar
EXPOSE 5000

ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Seoul" ,"app.jar"]
