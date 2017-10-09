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
    


## PCF Deployment

Goal of this exercise is to deploy this existing service to the PCF.

1. Login with CF CLI to the Pivotal Cloud Foundry


    #cf login -a https://api.local.pcfdev.io --skip-ssl-validation
    
2. Create manifest.yml


    ---
    applications:
    - name: <YOUR USERNAME or demo>
      memory: 800M
      random-route: true
      path: target/demo-0.0.1-SNAPSHOT.jar

3. Build and push you application


    mvn package    
    cf push

4. Check available applications


    cf apps
    
    cf logs <app name>
    
   