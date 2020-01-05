FROM openjdk:11

ADD webapp-demo.jar /usr/local/webapp-demo/

VOLUME /var/log/webapp-demo

WORKDIR /usr/local/webapp-demo/

CMD ["java",  "-jar", "webapp-demo.jar"]