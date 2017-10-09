# PCF Workshop

This workshop will teach you 
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
    
