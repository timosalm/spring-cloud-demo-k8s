# Demo for a typical Spring Cloud Architecture on Kubernetes

**See repository [here](https://github.com/tsalm-pivotal/spring-cloud-demo) for the same application deployed on TAS/PCF/CF**
**See repository [here](https://github.com/tsalm-pivotal/spring-cloud-demo-asc) for the same application deployed on Azure Spring Cloud**

## Deployment

1. Ensure you have a local Docker installed and running, then execute:
   ```
   CONTAINER_REGISTRY_PREFIX=<your-registry-prefix>  # If you are using Docker Hub as a registry the prefix is just your Docker Hub ID
   ./mvnw spring-boot:build-image -pl product-service -Dspring-boot.build-image.imageName=${CONTAINER_REGISTRY_PREFIX}/sc-product-service
   ./mvnw spring-boot:build-image -pl order-service -Dspring-boot.build-image.imageName=${CONTAINER_REGISTRY_PREFIX}/sc-order-service
   
   docker login # If you are not using Docker Hub you have to specify the host of the registry 
   docker push ${CONTAINER_REGISTRY_PREFIX}/sc-product-service
   docker push ${CONTAINER_REGISTRY_PREFIX}/sc-order-service
   ```
2. Create private registry secret resource
   ```
   kubectl create secret docker-registry regcred --docker-server=https://index.docker.io/v1/ --docker-username=<your-name> --docker-password=<your-pword> --docker-email=<your-email>
   ```
4. Pods with the spring-cloud-kubernetes dependency requires access to the Kubernetes API. 
   See information [here](https://docs.spring.io/spring-cloud-kubernetes/docs/current/reference/html/#service-account) and change/delete the [k8s-deployment/rbac.yaml](k8s-deployment/rbac.yaml) based on your requirements.       
5. Create all required Kubernetes resources
    ```
    kubectl create -f k8s-deployment
    ```