FROM tomcat:7.0

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/bookapi.war /usr/local/tomcat/webapps/bookapi.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
