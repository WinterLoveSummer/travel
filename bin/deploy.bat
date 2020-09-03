cd ..
cd travel-dependencies
call mvn clean deploy

cd ..
cd travel-common
call mvn clean deploy

cd ..
cd travel-common-domain
call mvn clean deploy

cd ..
cd travel-common-service
call mvn clean deploy

cd ..
cd travel-service-redis
call mvn clean deploy

cd ..
cd travel-service-sso
call mvn clean deploy


cd ..
cd travel-service-upload
call mvn clean deploy

cd ..
cd travel-service-scene
call mvn clean deploy

cd ..