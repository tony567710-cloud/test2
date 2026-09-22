FROM tomcat:11.0.26-jre25

ENV TZ="Asia/Taipei"

ARG WAR_FILE=xxx.war
COPY ./${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]