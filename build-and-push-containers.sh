set -e
if [ -z "$1" ]
then
  echo "Please set your container registry prefix as a parameter"
else
  for service in product-service order-service shipping-service gateway; do
    ./mvnw spring-boot:build-image -pl "$service" -Dspring-boot.build-image.imageName="$1/sc-$service"
    docker push "$1/sc-$service"
  done
fi