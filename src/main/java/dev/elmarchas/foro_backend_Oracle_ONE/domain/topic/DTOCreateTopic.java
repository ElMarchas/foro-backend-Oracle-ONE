package dev.elmarchas.foro_backend_Oracle_ONE.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOCreateTopic(

        @NotBlank String title,
        @NotBlank String message,
        @NotNull Long userId,
        @NotNull Long classroomId) {

}
