# OnlineMovieRentalSystem
## Add jstl-1.2.jar to tomcat/lib folder otherwise this app doesn't work
## Add mysql-connector-java-8.0.12.jar to tomcat/lib folder otherwise this app doesn't work
### We use remotemysql as our sql server which is an online free sql server, you can use the info below to login use workbench
### Hostname: remotemysql.com
### Port: 3306
### Username: 7nVxZhInjB
### Password: J2q22YGyY6


### update HTML/JSP
1. updated editMovie.jsp: need an extra input textfield for movieID, and added set MovieId in updateMovieController.java, otherwise moviedao.java cannot get movieid for sql query.
2. updated managerHome.jsp: typos displayed in manage movie section, should be movie but appears as employee 
3. updated BUG in searchMovieByActorController.java: should call getMoviesByActor but appears as getMoviesByName
