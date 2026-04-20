
jetty-base создаётся так:

java -jar $JETTY_HOME/start.jar --add-modules=server,http,ee10-deploy,ee10-plus,ee10-jsp,ee10-jstl

список нужных модулей можно получить:

java -jar $JETTY_HOME/start.jar --list-modules




