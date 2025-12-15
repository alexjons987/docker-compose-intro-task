package se.alexjons.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.alexjons.microservice.entity.Message;

@org.springframework.stereotype.Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
