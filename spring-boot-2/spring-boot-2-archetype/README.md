# Sprint Boot 2 Archetype

Run **mvn archetype:generate** specifying the following options:

- -DarchetypeGroupId=lockc.spring-boot
- -DarchetypeArtifactId=spring-boot-2-archetype 
- -DarchetypeVersion=1.0.0-SNAPSHOT 
- -DgroupId=*[YOUR GROUP ID]*
- -DartifactId=*[YOUR ARTIFACT NAME]* 
- -Dapplication-name=*[YOUR APPLICATION NAME FOR THE README]* 
- -Dapplication-description=*[A SHORT APPLICATION DESCRIPTION]* 
- -Dapplication-acronym=*[THE ACRONYM FOR THE NEW APPLICATION USED IN SYSTEM PROPERTIES]* 
- -Dpackage=com.pay360.*[YOUR APPLICATION NAME]* This will be the route package for your application 
- -Dversion=0.0.1-SNAPSHOT  

```mvn archetype:generate -DarchetypeGroupId=lockc.spring-boot -DarchetypeArtifactId=spring-boot-2-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DgroupId=lockc.spring-boot.examples -DartifactId=experiment -Dapplication-name=Experiment -Dapplication-description="Experiment with archetype generation" -Dapplication-acronym=exp -Dpackage=lockc.spring.examples.experiment -Dversion=0.0.1-SNAPSHOT```
