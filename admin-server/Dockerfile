FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/adminserver.jar
COPY ${JAR_FILE} adminserver.jar
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories && apk add --no-cache tini
ENTRYPOINT ["/sbin/tini", "--"]
CMD ["sh","-c","java -Dproject.name=adminserver -Djava.security.egd=file:/dev/./urandom -Duser.timezone=GMT+08 -jar adminserver.jar"]