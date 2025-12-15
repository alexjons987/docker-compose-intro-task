package se.alexjons.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.alexjons.microservice.dto.MessageRequestDTO;
import se.alexjons.microservice.dto.MessageResponseDTO;
import se.alexjons.microservice.entity.Message;
import se.alexjons.microservice.mapper.MessageMapper;
import se.alexjons.microservice.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageMapper messageMapper;

    public List<MessageResponseDTO>  getAllMessages() {
        return messageRepository.findAll().stream()
                .map(messageMapper::toResponseDTO)
                .toList();
    }

    public MessageResponseDTO addMessage(MessageRequestDTO dto) {
        Message message = new Message(dto.getMessage());

        Message saved = messageRepository.save(message);
        return messageMapper.toResponseDTO(saved);
    }
}
