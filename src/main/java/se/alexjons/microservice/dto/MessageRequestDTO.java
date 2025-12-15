package se.alexjons.microservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MessageRequestDTO {

    @NotNull
    @Size(min = 2, message = "message cannot be less than two characters")
    private String message;

    public MessageRequestDTO() {
    }

    public MessageRequestDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
