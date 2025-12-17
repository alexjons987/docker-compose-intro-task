# Simple docker swarm task
## 1. Initialize Docker Swarm
1. `docker swarm init` to initialize
2. `docker node ls` to verify that it has been initialized

## 2. Start MySQL as a service with 1 replica
1. `docker service create --name mysql --replicas 1 -e MYSQL_ROOT_PASSWORD=rootpassword -e MYSQL_DATABASE=testdb mysql:8.0` to start a replica of MySQL service
2. `docker service ls` & `docker service ps mysql` to verify

## 3. Create a table and insert data
1. `docker ps` to get the name of the MySQL service container name
2. `docker exec -it <NAME> mysql -u root -p`
3. Use the previously set password
4. Insert data into a table:
```sql
USE testdb;

CREATE TABLE messages(id INT AUTO_INCREMENT PRIMARY KEY, message VARCHAR(255));

INSERT INTO messages(message) VALUES ("Hello this is Swarm swarming swarm swarm");

SELECT * FROM messages;
```

## 4. Scale to 2 replicas
1. `docker service scale mysql=2`

## Explain: Why this is not load balancing and what can go wrong
This is not load balancing as Swarm handles scheduling, restarting and networking. Database replication and consistency is not a part of it. We are now running two databases.

One major thing that could go wrong is that you insert data in one of the replicas that you will later query. Later on if you are unlucky you will be routed to the second replica and your query will not return the expected data.


## 5. Remove services and leave the swarm
1. `docker service rm mysql` to remove running services
2. `docker swarm leave --force` to leave swarm
3. `docker info` to verify ("Swarm" shoud be `inactive`)