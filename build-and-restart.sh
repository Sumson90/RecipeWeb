docker build . -t przepisy
docker stop przepisy || true
docker rm przepisy || true
docker run -d -p 8080:8080 --name=przepisy -e SPRING_PROFILES_ACTIVE=prod --network przepisy-network --restart unless-stopped przepisy