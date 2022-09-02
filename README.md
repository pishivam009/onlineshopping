"# onlineshopping" 
1. Install Docker

2. MongoDB Docker Setup. Run the following commands in command line:
   
    mkdir mongodb-docker
    cd mongodb-docker
    docker run -d -p 2717:27017 -v ~/mongodb-docker:/data/db --name mymongo mongo:latest
    docker exec -it mymongo mongosh
    use product-service

3. Install MySQL. Create schemas name order-service and inventory-service

    mkdir mysql-docker
    cd mysql-docker
    docker run --name mysql -p 336:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql
    docker exec -it mysql mysql --user=root --password=password
    create database `order-service`;
    create database `inventory-service`;

4. Swagger UI docuemntation can be found for order-service, inventory-service and product service can be found at localhost:<service-port>/swagger-ui/index.html
5. Currently centralized swagger is unavailable
6. Apply them on localhost:8081
