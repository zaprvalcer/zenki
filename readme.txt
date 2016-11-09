1. install gradle
http://www.gradle.org/installation

2. install tomcat
http://tomcat.apache.org/tomcat-7.0-doc/RUNNING.txt
(optional: add admin user to tomcat-users.xml so you will be able to access manager)

3. Clone project from github

4. Generate zenki.ipr file and open it:
From cmd in Zenki/ folder run "gradle cleanIdea idea". Double-click on generated zenki.ipr

5. Create facet and artifact:
On idea panel click on "Project structure"->"Facets"-> "+" -> choose "Web"
- Edit deployment descriptors path to path to web.xml. For example, mine is "D:\Development\Zenki\src\main\webapp\WEB-INF\web.xml"
- Edit web resource directory path. For example, mine is "D:\Development\Zenki\src\main\webapp\". Rrelative path should stay "/".
click "Create Artifact" button
click "Fix..." button -> "Add all missing dependencies..."
click "OK"

6. Configure tomcat:
In main panel click "edit configuration", click "+", choose "Tomcat Server"->"Local".
In appeared window provide right path to your tomcat installation.
Implement artifacts: click "Fix" button, in application context make "/zenki"

7. Build Project:
From cmd in Zenki/ folder run "gradle clean build", this will build war

8. Run tomcat

9. Check. In browser open "http://localhost:8080/zenki/mainpage"
If you see anything that is not 404 error - congrats.

10. Additional setup:
Intellij panel "VCS"->"Enable Version Control integration...", choose Git
