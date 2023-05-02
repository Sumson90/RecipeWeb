call mvn clean package
docker build -t przepisy .
docker stop przepisy
docker rm przepisy
docker run -d -p 8080:8080 --name przepisy przepisy