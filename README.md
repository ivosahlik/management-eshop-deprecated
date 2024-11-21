# run app
mvn clean package -Dspring.profiles.active=dev -Dmaven.test.skip=true
mvn clean package -Dspring.profiles.active=prod -Dmaven.test.skip=true

-Dspring.profiles.active=dev

-Dspring.profiles.active=prod

-Dspring.profiles.active=dev -Dfeature.test.enabled=true

## remote debugging with intellij idea pres remote
java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 adminportal-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 adminportal-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --debug

java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 eshopweb-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=dev -Ddeployment.application=membrania --debug

## local test

java -jar -Dspring.profiles.active=dev -Dserver.port=8081 -Ddeployment.application=membrania adminportal-0.0.1-SNAPSHOT.jar -Xmx2048m -Xms1024m

java -jar -Dspring.profiles.active=dev -Dserver.port=8082 -Ddeployment.application=dcsolutions eshopweb-0.0.1-SNAPSHOT.jar -Xmx2048m -Xms1024m
java -jar -Dspring.profiles.active=dev -Dserver.port=8083 -Ddeployment.application=membrania eshopweb-0.0.1-SNAPSHOT.jar -Xmx2048m -Xms1024m

## docker run
docker run -p 8081:8081 --network="host" -e JAVA_TOOL_OPTIONS='-Dspring.profiles.active=docker -Dserver.port=8081 -Ddeployment.application=membrania -Xmx2048m -Xms1024m' ivosahlik/managementeshop:latest
docker run -p 8081:8081 --network="host" -e JAVA_TOOL_OPTIONS='-Dspring.profiles.active=docker -Dserver.port=8081 -Ddeployment.application=membrania -Xmx2048m -Xms1024m' ivosahlik/eshopweb:0.0.2-SNAPSHOT
docker run -p 8081:8080 -e JAVA_TOOL_OPTIONS='-Dspring.profiles.active=docker -Dserver.port=8080 -Ddeployment.application=membrania -Xmx2048m -Xms1024m' ivosahlik/eshopweb:0.0.2-SNAPSHOT


# ultrapurewater - adminportal
mvn spring-boot:run -Dspring.profiles.active=prod

nohup java -jar -Dserver.port=8083 -Dspring.profiles.active=prod  /home/workspace/managementeshop/adminportal/target/adminportal-0.0.1-SNAPSHOT.jar 2>log.out &

# ultrapurewater - shop
nohup java -jar -Xmx1024m -Xms256m -Dserver.port=8080 -Dspring.profiles.active=prod -Ddeployment.application=membrania /home/workspace/managementeshop/eshopweb/target/eshopweb-0.0.1-SNAPSHOT.jar 2>log.out &

nohup java -jar -Xmx2048m -Xms256m -Dserver.port=8080 -Dspring.profiles.active=prod -Ddeployment.application=membrania /home/workspace/managementeshop/eshopweb/target/eshopweb-0.0.1-SNAPSHOT.jar 2>log.out &

nohup java -jar -Xmx3072m -Xms256m -Dserver.port=8080 -Dspring.profiles.active=prod -Ddeployment.application=membrania eshopweb-0.0.1-SNAPSHOT.jar 2>log.out &

https://stackoverflow.com/questions/31038250/setting-active-profile-and-config-location-from-command-line-in-spring-boot


# set hosts on the mac
less /etc/hosts
sudo vim /etc/hosts
127.0.0.1       localhost.ultrapurewater.eu

localhost.ultrapurewater.eu:8080

# mysql
https://www.a2hosting.com/kb/developer-corner/mysql/managing-mysql-databases-and-users-from-the-command-line

## mysql step by step
mysql -u root -p
SQL:
show databases;
use name-database;

Select * from mysql.user;
DROP USER 'account_name';

!! important !!
DROP DATABASE dbprodultrapurewater;
CREATE DATABASE dbprodultrapurewater;
CREATE SCHEMA `dbprodultrapurewater` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_czech_ci;
GRANT ALL PRIVILEGES ON *.* TO 'ultrapurewater'@'localhost' IDENTIFIED BY 'Vosa1234.+-UltraPureWater+Adam&';
GRANT ALL PRIVILEGES ON *.* TO 'ultrapurewater'@'mysql' IDENTIFIED BY 'Vosa1234.+-UltraPureWater+Adam&' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'ultrapurewater'@'%' IDENTIFIED BY 'Vosa1234.+-UltraPureWater+Adam&' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'ultrapurewater'@'%' IDENTIFIED BY 'dummypassword' WITH GRANT OPTION;

DROP USER 'ultrapurewater'@'localhost';
CREATE USER 'ultrapurewater'@'localhost' IDENTIFIED BY 'Vosa1234.+-UltraPureWater+Adam&';
CREATE USER 'ultrapurewater'@'mysql' IDENTIFIED BY 'Vosa1234.+-UltraPureWater+Adam&';
FLUSH PRIVILEGES;
GRANT SELECT ON * . * TO 'ultrapurewater'@'localhost';
GRANT SELECT ON * . * TO 'ultrapurewater'@'mysql';
GRANT ALL PRIVILEGES ON `dbprodultrapurewater` . * TO 'ultrapurewater'@'localhost';
GRANT ALL PRIVILEGES ON `dbprodultrapurewater` . * TO 'ultrapurewater'@'mysql';
FLUSH PRIVILEGES;

SHOW GRANTS FOR 'ultrapurewater'@'localhost';
SHOW GRANTS FOR 'ultrapurewater'@'%';
REVOKE ALL ON `dbprodultrapurewater`.* FROM 'ultrapurewater'@'localhost';

GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY '%password%' WITH GRANT OPTION;


SELECT user,authentication_string,plugin,host FROM mysql.user;

SET FOREIGN_KEY_CHECKS = 0;


kubectl exec -it mysql-75d48ffc74-kbjd7 -- bash
kubectl exec -it mysql-75d48ffc74-kbjd7 -- mysql -u ultrapurewater -p'Vosa1234.+-UltraPureWater+Adam&'
kubectl exec -it mysql-75d48ffc74-kbjd7 -- mysql -u ultrapurewater -p'Vosa1234.+-UltraPureWater+Adam&'
mysql -u ultrapurewater -p'Vosa1234.+-UltraPureWater+Adam&'
mysql -u root -p'dummypassword'
mysql -u ultrapurewater -p'dummypassword'

GRANT DELETE,SELECT,INSERT,UPDATE ON dbprodultrapurewater.* TO 'ultrapurewater'@'mysql' BY 'Vosa1234.+-UltraPureWater+Adam&';
ALTER SCHEMA `dbprodultrapurewater`  DEFAULT COLLATE utf8mb4_czech_ci ;
ALTER SCHEMA `dbprodultrapurewater` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_czech_ci;




# scp
## from remote to local
scp root@31.31.74.128:/tmp/db_prod/data-dump.sql .
## from local to remote
scp data-dump.sql root@89.221.215.189:/tmp/prod-db
scp dbprodultrapurewater_*  root@89.221.215.189:/tmp/prod-db-new
### THE BEST :)
scp dbprodultrapurewater_*  root@89.221.215.189:/tmp/prod-db-new








# mysql export db tables
mysql -u root -p#Vosa1234_@$~^# dbprodultrapurewater > dbprodultrapurewater_banners.sql
mysqldump -u root -p#Vosa1234_@$~^# dbprodultrapurewater > dbprodultrapurewater_banners.sql

# logback
https://www.mkyong.com/logging/logback-xml-example/

# banner
https://devops.datenkollektiv.de/banner.txt/index.html

# skip test
mvn package -Dmaven.test.skip=true

# redirect
#
# Use name-based virtual hosting.
#
NameVirtualHost *:80
<VirtualHost *:80>
ServerName re-col.com
Redirect / https://www.membrania.eu/category/recycled-antifreeze
</VirtualHost>



### k8s

kubectl config set-context --current --namespace=web2-cz-dev
kubectl config set-context --current --namespace=web2-cz-prod
kubectl config view | grep namespace:
kubectl scale deployment eshop-membrania-web-application --replicas=2

kubectl apply -f mysql-deployment.yaml,mysql-service.yaml,mysql-database-data-volume-persistentvolumeclaim.yaml
kubectl delete -f mysql-deployment.yaml,mysql-service.yaml,mysql-database-data-volume-persistentvolumeclaim.yaml

kubectl apply -f todo-web-application-deployment.yaml,todo-web-application-service.yaml,todo-web-application-secret.yaml,todo-web-application-config-map.yaml
kubectl delete -f todo-web-application-deployment.yaml,todo-web-application-service.yaml,todo-web-application-secret.yaml,todo-web-application-config-map.yaml

# admin membrania
kubectl apply -f admin-web-application-deployment.yaml,admin-web-application-service.yaml,admin-web-application-secret.yaml,letsencrypt-cert.yml,letsencrypt-issuer.yml
kubectl delete -f admin-web-application-deployment.yaml,admin-web-application-service.yaml,admin-web-application-secret.yaml,letsencrypt-cert.yml,letsencrypt-issuer.yml
kubectl apply -f admin-web-application-deployment.yaml,admin-web-application-service.yaml
kubectl delete -f admin-web-application-deployment.yaml,admin-web-application-service.yaml



# membrania
kubectl apply -f eshop-membrania-web-application-deployment.yaml,eshop-membrania-web-application-service.yaml,eshop-membrania-web-application-secret.yaml,letsencrypt-cert.yml,letsencrypt-issuer.yml
kubectl delete -f eshop-membrania-web-application-deployment.yaml,eshop-membrania-web-application-service.yaml,eshop-membrania-web-application-secret.yaml,letsencrypt-cert.yml,letsencrypt-issuer.yml
kubectl apply -f letsencrypt-cert.yml,letsencrypt-issuer.yml
kubectl apply -f eshop-membrania-web-application-deployment.yaml,eshop-membrania-web-application-service.yaml
kubectl delete -f eshop-membrania-web-application-deployment.yaml,eshop-membrania-web-application-service.yaml

# dcsolutions
kubectl apply -f eshop-dcsolutions-web-application-deployment.yaml,eshop-dcsolutions-web-application-service.yaml,eshop-dcsolutions-web-application-secret.yaml,letsencrypt-cert.yml,letsencrypt-issuer.yml
kubectl delete -f eshop-dcsolutions-web-application-deployment.yaml,eshop-dcsolutions-web-application-service.yaml,eshop-dcsolutions-web-application-secret.yaml,letsencrypt-cert.yml,letsencrypt-issuer.yml
kubectl apply -f eshop-dcsolutions-web-application-deployment.yaml,eshop-dcsolutions-web-application-service.yaml
kubectl delete -f eshop-dcsolutions-web-application-deployment.yaml,eshop-dcsolutions-web-application-service.yaml

kubectl exec -it todo-web-application-7cfd5d6f54-7hwrh -- bash


###### CERTIFICATE ######
https://www.digitalocean.com/community/tutorials/how-to-set-up-an-nginx-ingress-with-cert-manager-on-digitalocean-kubernetes
kubectl describe certificate

kubectl describe certificate -n web2-cz-prod dcsolutions.cz.info-tls
kubectl describe certificate -n web2-cz-prod membrania.eu.info-tls

https://cert-manager.io/docs/installation/
kubectl get cert-manager --namespace=cert-manager


kubectl get issuers svj4u-cz-ca-issuer -n dev-cz -o wide
https://phoenixnap.com/kb/kubernetes-ssl-certificates

!!!!!!! important !!!!!!!!
kubectl create secret tls ssl-secret --key www.svj4u.cz.key --cert www.svj4u.cz.cer
->>>>>>> secret/ssl-secret created
curl https://svj4u.cz -kv
kubectl get secret ssl-secret -o yaml

# mysql import
mysql -u root -p dbprodultrapurewater < dbprodultrapurewater_product.sql
mysql -u root -p dbprodultrapurewater < dbprodultrapurewater_type.sql

mysql -u ultrapurewater -pdummypassword < dbprodultrapurewater_banners.sql

# mysql k8s
kubectl exec -it mysql-75d48ffc74-kbjd7 -- mysql -u root -pVosa12345.root@#. dbprodultrapurewater < data-dump.sql
kubectl exec -i mysql-bd5b57d6b-lbm2x -- mysql -u root -p dummypassword < dbprodultrapurewater_banners.sql

kubectl cp dbprodultrapurewater_banners.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_banners.sql
kubectl cp dbprodultrapurewater_banners_product_dto_list.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_banners_product_dto_list.sql
kubectl cp dbprodultrapurewater_billing_address.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_billing_address.sql
kubectl cp dbprodultrapurewater_cart_item.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_cart_item.sql
kubectl cp dbprodultrapurewater_category.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_category.sql
kubectl cp dbprodultrapurewater_countries.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_countries.sql
kubectl cp dbprodultrapurewater_custom_settings.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_custom_settings.sql
kubectl cp dbprodultrapurewater_dhl_transport.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_dhl_transport.sql
kubectl cp dbprodultrapurewater_message.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_message.sql
kubectl cp dbprodultrapurewater_password_reset_token.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_password_reset_token.sql
kubectl cp dbprodultrapurewater_payment.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_payment.sql
kubectl cp dbprodultrapurewater_product.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_product.sql
kubectl cp dbprodultrapurewater_product_dto.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_product_dto.sql
kubectl cp dbprodultrapurewater_product_to_cart_item.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_product_to_cart_item.sql
kubectl cp dbprodultrapurewater_product_to_category_dto.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_product_to_category_dto.sql
kubectl cp dbprodultrapurewater_request_pricing.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_request_pricing.sql
kubectl cp dbprodultrapurewater_role.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_role.sql
kubectl cp dbprodultrapurewater_files.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_files.sql
kubectl cp dbprodultrapurewater_settings.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_settings.sql
kubectl cp dbprodultrapurewater_shipping_address.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_shipping_address.sql
kubectl cp dbprodultrapurewater_shopping_cart.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_shopping_cart.sql
kubectl cp dbprodultrapurewater_sub_category.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_sub_category.sql
kubectl cp dbprodultrapurewater_support.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_support.sql
kubectl cp dbprodultrapurewater_tariff_zone.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_tariff_zone.sql
kubectl cp dbprodultrapurewater_upload_file.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_upload_file.sql
kubectl cp dbprodultrapurewater_user.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_user.sql
kubectl cp dbprodultrapurewater_user_billing.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_user_billing.sql
kubectl cp dbprodultrapurewater_user_order.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_user_order.sql
kubectl cp dbprodultrapurewater_user_payment.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_user_payment.sql
kubectl cp dbprodultrapurewater_user_role.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_user_role.sql
kubectl cp dbprodultrapurewater_user_shipping.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_user_shipping.sql
kubectl cp dbprodultrapurewater_video_banner.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_video_banner.sql
kubectl cp dbprodultrapurewater_video_streams.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_video_streams.sql
kubectl cp dbprodultrapurewater_type.sql mysql-bd5b57d6b-lbm2x:/tmp/dbprodultrapurewater_type.sql

kubectl cp data-dump.sql mysql-75d48ffc74-kbjd7:/tmp/data-dump.sql


### copy sql files from cluster to mysql pod
scp dbprodultrapurewater_banners root@89.221.215.189:/tmp/prod-db
### mysql install data to database
pod
kubectl exec -it mysql-bd5b57d6b-lbm2x -- bash
cd /tmp
mysql --host=localhost --user=root --password=dummypassword dbprodultrapurewater < dbprodultrapurewater_type.sql
mysql --host=localhost --user=root --password=Vosa12345.root@#. dbprodultrapurewater < data-dump.sql

### mysql import data to database
#prod
mysql -u ultrapurewater -p'Vosa1234.+-UltraPureWater+Adam&' dbprodultrapurewater < dbprodultrapurewater_banners.sql
#dev
mysql --user=ultrapurewater --password=dummypassword dbprodultrapurewater < dbprodultrapurewater_banners.sql
mysql --host=localhost --user=root --password=#Vosa1234_@$~^# dbprodultrapurewater > dbprodultrapurewater_banners.sql




-rw-r--r--  1 root root      983 Feb 19 12:50 dbprodultrapurewater_banners.sql
-rw-r--r--  1 root root        0 Feb 19 13:05 dbprodultrapurewater_banners_product_dto_list.sql
-rw-r--r--  1 root root    21809 Feb 19 13:05 dbprodultrapurewater_billing_address.sql
-rw-r--r--  1 root root     6341 Feb 19 13:05 dbprodultrapurewater_cart_item.sql
-rw-r--r--  1 root root  2380263 Feb 19 13:06 dbprodultrapurewater_category.sql
-rw-r--r--  1 root root    19863 Feb 19 13:06 dbprodultrapurewater_countries.sql
-rw-r--r--  1 root root     6453 Feb 19 13:06 dbprodultrapurewater_custom_settings.sql
-rw-r--r--  1 root root     4074 Feb 19 13:06 dbprodultrapurewater_dhl_transport.sql
-rw-r--r--  1 root root 44373614 Feb 19 13:07 dbprodultrapurewater_files.sql
-rw-r--r--  1 root root     1774 Feb 19 13:07 dbprodultrapurewater_message.sql
-rw-r--r--  1 root root    15022 Feb 19 13:08 dbprodultrapurewater_password_reset_token.sql
-rw-r--r--  1 root root     9506 Feb 19 13:09 dbprodultrapurewater_payment.sql
-rw-r--r--  1 root root 24852202 Feb 19 13:09 dbprodultrapurewater_product.sql
-rw-r--r--  1 root root        0 Feb 19 13:10 dbprodultrapurewater_product_dto.sql
-rw-r--r--  1 root root     3250 Feb 19 13:10 dbprodultrapurewater_product_to_cart_item.sql
-rw-r--r--  1 root root        0 Feb 19 13:10 dbprodultrapurewater_product_to_category_dto.sql
-rw-r--r--  1 root root    60263 Feb 19 13:11 dbprodultrapurewater_request_pricing.sql
-rw-r--r--  1 root root      159 Feb 19 13:11 dbprodultrapurewater_role.sql
-rw-r--r--  1 root root     2677 Feb 19 13:11 dbprodultrapurewater_settings.sql
-rw-r--r--  1 root root    22383 Feb 19 13:11 dbprodultrapurewater_shipping_address.sql
-rw-r--r--  1 root root    16923 Feb 19 13:11 dbprodultrapurewater_shopping_cart.sql
-rw-r--r--  1 root root  2912240 Feb 19 13:12 dbprodultrapurewater_sub_category.sql
-rw-r--r--  1 root root        0 Feb 19 13:12 dbprodultrapurewater_support.sql
-rw-r--r--  1 root root     3069 Feb 19 13:12 dbprodultrapurewater_tariff_zone.sql
-rw-r--r--  1 root root  5796522 Feb 19 13:13 dbprodultrapurewater_type.sql
-rw-r--r--  1 root root  2765388 Feb 19 13:13 dbprodultrapurewater_upload_file.sql
-rw-r--r--  1 root root    19229 Feb 19 13:13 dbprodultrapurewater_user.sql
-rw-r--r--  1 root root        0 Feb 19 13:14 dbprodultrapurewater_user_billing.sql
-rw-r--r--  1 root root    22177 Feb 19 13:14 dbprodultrapurewater_user_order.sql
-rw-r--r--  1 root root        0 Feb 19 13:14 dbprodultrapurewater_user_payment.sql
-rw-r--r--  1 root root     5529 Feb 19 13:14 dbprodultrapurewater_user_role.sql
-rw-r--r--  1 root root     5791 Feb 19 13:14 dbprodultrapurewater_user_shipping.sql
-rw-r--r--  1 root root      318 Feb 19 13:15 dbprodultrapurewater_video_banner.sql
-rw-r--r--  1 root root      240 Feb 19 13:15 dbprodultrapurewater_video_streams.sql




SET FOREIGN_KEY_CHECKS = 0;

 banners                   ok   (3)   
 banners_product_dto_list       ok
 billing_address          CONSTRAINT      
 cart_item           CONSTRAINT           
 category         ok / Unknown column '0x' in 'field list'              
 countries       ok               
 custom_settings     ok (2)            
 dhl_transport     ok -> ALTER TABLE dhl_transport MODIFY id int NOT NULL AUTO_INCREMENT;             
 files          ok                
 message   ok                     
 password_reset_token     ok      
 payment           CONSTRAINT             
 product        ok                
 product_dto           ok         
 product_to_cart_item       CONSTRAINT    
 product_to_category_dto      ok  
 request_pricing   ok             
 role      ok                     
 settings     ok (1)                  
 shipping_address       CONSTRAINT        
 shopping_cart     ok             
 sub_category    ok               
 support      ok                  
 tariff_zone    ok -> ALTER TABLE tariff_zone MODIFY id int NOT NULL AUTO_INCREMENT;                    
 type        ok   (4)                      
 upload_file        ok (5)            
 user           ok                
 user_billing      ok             
 user_order   CONSTRAINT                  
 user_payment       ok            
 user_role      ok   depend on role             
 user_shipping      ok            
 video_banner       ok (6)            
 video_streams  ok (7)

