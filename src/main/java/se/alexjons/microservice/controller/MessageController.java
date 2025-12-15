package se.alexjons.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.alexjons.microservice.dto.MessageRequestDTO;
import se.alexjons.microservice.dto.MessageResponseDTO;
import se.alexjons.microservice.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MessageResponseDTO>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> addNewMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        return ResponseEntity.ok(messageService.addMessage(messageRequestDTO));
    }
}
