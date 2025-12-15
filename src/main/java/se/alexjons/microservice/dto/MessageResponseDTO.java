package se.alexjons.microservice.dto;

public class MessageResponseDTO {

    private Long id;
    private String message;

    public MessageResponseDTO() {
    }

    public MessageResponseDTO(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
