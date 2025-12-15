package se.alexjons.microservice.mapper;

import org.springframework.stereotype.Component;
import se.alexjons.microservice.dto.MessageResponseDTO;
import se.alexjons.microservice.entity.Message;

@Component
public class MessageMapper {

    public MessageResponseDTO toResponseDTO(Message message) {
        if (message == null) return null;

        return new MessageResponseDTO(
                message.getId(),
                message.getMessage()
        );
    }
}
