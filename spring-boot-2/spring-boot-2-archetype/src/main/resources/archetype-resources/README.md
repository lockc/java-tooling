# ${rootArtifactId}

[Describe the application]

## Configuration

The application requires the program argument _--spring.config.location_ to be set.  This points to the file that provides the application
properties.
 
**Required Properties:**

| Name        | Description |
|-------------|-------------|
| spring.config.location    | The paths to the configuration application configuration properties, comma separated list and must include the classpath root e.g. 'classpath:/,/path/to/config/application.properties' |
| [OTHER REQUIRED PROPERTIES    | [THEIR DESCRIPTION] |


**Optional Properties:**

| Name        | Description | Default |
|-------------|-------------|---------|
| server.port | The HTTP port to serve the application over | 8080 |
| server.context-path | The base context path of the web application | /${rootArtifactId} |
| logging.config | The logback log configuration file to use  | _{install directory}_/config/logback.xml |
| ${application-acronym}.healthCheck.url| URL that the Service uses for talking to itself for Health Checks. | http://localhost:${server.port}/${server.context-path} |
| ${application-acronym}.healthCheck.cache.time.secs | How long in seconds to cache any health check information, defaults to 0 if not set | 0 |
| ${application-acronym}.healthCheck.window.size.secs | The time period over which to calculate metrics, generally used for the sliding count metrics | 10 |
| [OTHER OPTIONAL PROPERTIES]| [THEIR DESCRIPTION] | [THEIR DEFAULT VALUE] |


## Health Checks

### Build info

To add a value to be included in the standard actuator build info add it as an additionalProperty of the spring-boot-maven-plugin build-info goal of the ${rootArtifactId}

#### Build info endpoint

To get the build info

```GET actuator/info```

### Spring Boot health indicators
To add a standard spring boot or custom health indicator add a line for each spring boot health indicator required:

```
health.bootHealthIndicators.<classname>=<description>
```

*classname* is the camelCase version of the class  (without a leading capital letter).

*description* is the text that will will be displayed in the health controller page.

For example to add the standard data source health indicator add the following line :

```
health.bootHealthIndicators.dataSourceHealthIndicator=Data Source health
```

### Custom metrics health indicators

[DESCRIBE IF PRESENT OTHERWISE REMOVE THIS SECTION]

### Metrics endpoint

#### actuator/allmetrics

This endpoint called with no parameters will list all the metrics in the application at the top level, a mix of all metrics will 
be listed.

#### actuator/allmetrics/{pattern match name}

This endpoint will limit the metrics to the regular expression
actuator/allmetrics/${application-acronym}.* will match all metrics starting with ${application-acronym}

#### actuator/allmetrics/{pattern match name}?tag={tags}
This endpoint will match the specified metric pattern and also only return the values that match the listed tags the format of the tags is a comma separated list of "NAME:VALUE" where NAME is the tag name and VALUE is the exact value of the tag

the example below will match all metrics starting with jvm.memory and extract data for values with the tags of area noheap and id of Metaspace

actuator/allmetrics/jvm.memory.*?tag=area:nonheap,id:Metaspace

this will produce the following output 

```
[
    {
        "name": "jvm.memory.max",
        "description": "The maximum amount of memory in bytes that can be used for memory management",
        "baseUnit": "bytes",
        "measurements": [
            {
                "statistic": "VALUE",
                "value": -1
            }
        ],
        "availableTags": []
    },
    {
        "name": "jvm.memory.used",
        "description": "The amount of used memory",
        "baseUnit": "bytes",
        "measurements": [
            {
                "statistic": "VALUE",
                "value": 54983128
            }
        ],
        "availableTags": []
    },
    {
        "name": "jvm.memory.committed",
        "description": "The amount of memory in bytes that is committed for the Java virtual machine to use",
        "baseUnit": "bytes",
        "measurements": [
            {
                "statistic": "VALUE",
                "value": 57974784
            }
        ],
        "availableTags": []
    }
]
```

### Excluding metrics

#### Excluding Global metrics
To exclude one or more global metrics add a partial or full match of the metrics' names to the *application.metrics.global-exclude-metrics* property.

```application.metrics.global-exclude-metrics=jvm,logback,tomcat,system,jdbc,process,hikaricp,http,${application-acronym}```

To exclude one or more global metrics by tag add the tag name to the *application.metrics.global-exclude-tags* property.

```application.metrics.global-exclude-tags=trace```

In both cases a value of **ALL** can be provided to exclude all global metrics, or all global metric by tag.

## Deployment

The ${application-name} will be a standalone Spring Boot application intended NOT to be run within a JBOSS container.  The main mode
of deployment for the service should be via the RPM

### Artefacts

The default build will produce the following artefacts

| Artefact    | Description |
|-------------|-------------|
| ${rootArtifactId}-{version}.jar | The jar file is a Spring Boot fat jar ready to be run on the JVM |
| ${rootArtifactId}-{version}.rpm | An installable rpm containing the binaries, configuration and start/stop script |

Maven installs the RPM it into the repository with it's Mavenified name which is not ideal e.g.

```
${rootArtifactId}-1.0.0-SNAPSHOT<timestamp>.rpm
```

this is renamed when the RPM is downloaded by the bundle or deployment jobs

### BlueBox

The service is deployed to the Bluebox as a standalone application via the blue-environment-setup project, the module is called **[DEPLOYMENT JOB MODULE NAME IN BLUE ENVIRONMENT SETUP]**

```
mvn clean install 
```

### RPM

The generated RPM can be found here

```
./${rootArtifactId}-webapp/target/rpm/${rootArtifactId}/RPMS/noarch/${rootArtifactId}-1.0.0-SNAPSHOT<timestamp>.noarch.rpm
```

The RPM is built using the rpm-maven-plugin, the following Maven properties can be configured

| Property    | Description | Default |
|-------------|-------------|-------------|
| app.name | The name of the application, this will control the name of the service and the directory it is installed under | ${rootArtifactId} |
| app.install.dir | The target installation directory of the application | /usr/local/spring |
| app.config.dir | The target configuration directory of the application | /etc/local/spring |
| app.log.dir | The target log directory of the application | /var/log/local/spring |
| app.service.user | The name of the service user used for file and directory permissions | service_${application-acronym} |
| app.service.owner | The default filesystem owner of installed files and directories | root |

RPM directory structure:

| Path    | Type | Owner | Group | Mode |
|-------------|-------------|-------------|-------------|-------------|
| /var/log/local/spring/${rootArtifactId}/                                   | Directory | service_${application-acronym} | adm         | 0750 |
| /etc/init.d/${rootArtifactId}                                              | File      | root        | root        | 0755 |
| /etc/sysconfig/${rootArtifactId}                                           | File      | root        | root        | 0640 |
| /var/run/${rootArtifactId}                                                 | Directory | root        | root        | 0755 |
| /etc/local/spring/${rootArtifactId}/                                       | Directory | root        | service_${application-acronym} | 0750 |
| /etc/local/spring/${rootArtifactId}/application.properties                 | File      | root        | service_${application-acronym} | 0640 |
| /etc/local/spring/${rootArtifactId}/logback.xml                            | File      | root        | service_${application-acronym} | 0640 |
| /usr/local/spring/${rootArtifactId}                                        | Directory | root        | service_${application-acronym} | 0750 |
| /usr/local/spring/${rootArtifactId}/lib                                    | Directory | root        | service_${application-acronym} | 0750 |
| /usr/local/spring/${rootArtifactId}/lib/${rootArtifactId}-1.0-SNAPSHOT.jar | File      | root        | service_${application-acronym} | 0640 |

## Development

### Requirements for building an RPM

To be able to build the RPM in the webapp project the *rpmbuild* package is required.  Centos based distributions should have *rpmbuild* as
standard but for Debian based distributions it can be added by installing Alien which bundles *rpmbuild*:

```
sudo apt-get install alien
```

To install the RPM on your Debian development environment you use the following:

```
sudo alien --scripts -i ${rootArtifactId}-1.0.0-SNAPSHOT20180822142933.noarch.rpm
```

To install the RPM on a Centos based system

```
rpm -i ${rootArtifactId}-1.0.0-SNAPSHOT20180822142933.noarch.rpm
```

### Build/Package the Application

```
mvn clean install
```

By default this will execute unit tests, integration tests and package the application as stand alone executable jar.

The executable jar file can can be started using the following (the preferred option is to start it via the Maven profile _LOCAL_):

```
java -jar ${rootArtifactId}-webapp-1.0-SNAPSHOT.jar --spring.config.location=classpath:/,<path to config file(s)>
```

### Running Locally

#### Via Maven

From the ${rootArtifactId}-webapp directory run: 

```
mvn clean spring-boot:run -PLOCAL
```

n.b. the <b>properties-maven-plugin</b> is used to set the required system properties in the webapp module's pom.

This will start the Spring Boot application locally using the bluebox dependencies, by default the bluebox.properties file will be loaded.
To start it against another environment run the following

```
mvn clean spring-boot:run -PLOCAL -Denv=pbjboss2
```

#### Via IDE

Create a Run configuration for the ServiceStartClass class, be sure to configure the required system properties
in the launcher as per the <b>properties-maven-plugin</b> used for the maven approach.

Execute the Run configuration via the IDE