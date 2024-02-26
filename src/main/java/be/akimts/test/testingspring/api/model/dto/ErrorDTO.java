package be.akimts.test.testingspring.api.model.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public record ErrorDTO(
        String message,
        HttpStatus status
) {}
