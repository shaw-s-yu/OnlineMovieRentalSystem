# OnlineMovieRentalSystem
## Add jstl-1.2.jar to tomcat/lib folder otherwise this app doesn't work
## Add mysql-connector-java-8.0.12.jar to tomcat/lib folder otherwise this app doesn't work

## sql database login:
username: root <br>
password: Fuckdamn123


### update HTML/JSP for extra credit
1. updated editMovie.jsp: need an extra input textfield for movieID, and added set MovieId in updateMovieController.java, otherwise moviedao.java cannot get movieid for sql query.
2. updated managerHome.jsp: typos displayed in manage movie section, should be movie but appears as employee 
3. updated BUG in searchMovieByActorController.java: should call getMoviesByActor but appears as getMoviesByName
4. updated index.jsp, home.jsp, managerHome,jsp, customerRepresentativeHome.jsp add if(email!=null || role!=null): redirect to login page if not already logged in.

### TEAM
1.Shenghui Yu(shenghui.yu@stonybrook.edu) <br>
2.Yiu Chung Yau(yiuchung.yau@stonybrook.edu) <br>
3.Haoyu Lu(haoyu.lu@stonybrook.edu)

### file
our war file: OnlineMovieRentalSystem/document/YYL_PROJECT.war
our database dump file: OnlineMovieRentalSystem/document/project.sql


