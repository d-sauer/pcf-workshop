# PCF Workshop

This workshop will teach you
 - how to push you application to PCF 
 - how to create MySQL instance
 - how to change existing Spring Boot application to connect to PCF MySql database
 - how to connect to existing ActiveMQ outside PCF.
 
## REST

### Send message

    curl -H "Content-Type: application/json" -X POST -d '{"message":"hello world!"}' http://localhost:8080/message

### List all messages

    curl -i http://localhost:8080/messages
    
### List my messages

    curl -i http://localhost:8080/my-messages
    
    
## Local development

### ActiveMQ

    docker run --name='activemq' -p 8161:8161 -p 61616:61616 webcenter/activemq:latest    
    


# PCF Deployment

## Deploying application

Goal of this exercise is to deploy this existing service to the PCF.

1. Login with CF CLI to the Pivotal Cloud Foundry


    #cf login -a https://api.local.pcfdev.io --skip-ssl-validation
    
2. Create manifest.yml


    ---
    applications:
    - name: <APP NAME>
      memory: 800M
      random-route: true
      path: target/demo-0.0.1-SNAPSHOT.jar

3. Build and push you application


    mvn package    
    cf push

4. Check available applications


    cf apps
    
    cf logs <app name>
    
    
## Create MySql service

1. List available services:


    cf m
    
2. Create MySql service


    cf create-service p-mysql 512mb <APP NAME>-mysql
    

> to delete service use `cf delete-service test`
> to list available services in the current space: `cf s`

3. Update application manifest.yml

Add new created service to the manifest.yml

    ---
    applications:
    - name: <APP NAME>
      memory: 800M
      random-route: true
      path: target/demo-0.0.1-SNAPSHOT.jar
      services:
        - <APP NAME>-mysql
        
4. Update service to use PCF service broker

4.1. Add specific cloud config


    package org.worhshop.demo.configuration;
    
    import org.springframework.cloud.config.java.AbstractCloudConfig;
    import org.springframework.context.annotation.Bean;
    
    import javax.sql.DataSource;
    
    public class CloudConfig extends AbstractCloudConfig {
    
        @Bean
        public DataSource demoDataSource() {
            return connectionFactory().dataSource();
        }
    }    
    
## Connect to external ActiveMQ

1. Update manifest.yml
    
    
    
    ---
    applications:
    - name: <APP NAME>
      memory: 800M
      random-route: true
      path: target/demo-0.0.1-SNAPSHOT.jar
      services:
        - <APP NAME>-mysql
      env:
        SPRING-ACTIVEMQ-BROKER-URL: tcp://10.10.18.79:61616
        SPRING-ACTIVEMQ-USER: admin
        SPRING-ACTIVEMQ-PASSWORD: admin
        
        
2. Send message to application


    curl -i -H "Content-Type: application/json" -X POST -d '{"message":"test1"}' http://demo.local.pcfdev.io/message

3. Get all messages

    
    curl  http://demo.local.pcfdev.io/messages



# Useful commands 

- Tail logs: `cf logs <app name>`
- Delete application: `cf delete <app name>`
- List all application: `cf apps`
- Delete service: `cf delete-service <service name>`

    
# Resources:

- https://docs.pivotal.io/pivotalcf/1-12/installing/highlights.html
- https://docs.pivotal.io/pivotalcf/1-12/concepts/understand-cf-networking.html
- https://pivotal.io/platform
- https://pivotal.io/pcf-dev
- https://docs.pivotal.io/pivotalcf/1-12/buildpacks/java/configuring-service-connections/spring-service-bindings.html