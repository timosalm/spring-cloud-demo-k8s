# Hints for running Spring Cloud Gateway on TAS/PCF
- It's necessary to align the versions of SCS tile, client libraries, boot and spring cloud versions, 
  see information [here](https://docs.pivotal.io/spring-cloud-services/2-0/common/client-dependencies.html#including-spring-cloud-services-dependencies).
  
  Retrieve the SCS tile version in PCF: 
     ```
     curl https://spring-cloud-broker.YOUR_APPS_DOMAIN/actuator/info
     ```
  
  Tested with SCS tile version **2.1.5-build.6**!
- CSRF has to be disabled, see implementation [here](src/main/java/com/example/gateway/WebSecurityConfiguration.java)
- It's necessary to exclude the `EurekaInstanceAutoConfiguration.class`, 
  see [GatewayApplication class](src/main/java/com/example/gateway/GatewayApplication.java)
