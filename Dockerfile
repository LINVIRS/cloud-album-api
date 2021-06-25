#FROM harbor.njbandou.com/library/store/oracle/serverjre:8
FROM registry.cn-hangzhou.aliyuncs.com/library-bd/openjdk:8-jdk-alpine
LABEL maintainer="wanglin@ssy.com"

WORKDIR /app
COPY api/target/cloud-album-api.jar ./app.jar

ENV TZ Asia/Shanghai
ENV JAVA_OPS -Duser.timezone=Asia/Shanghai

EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]
