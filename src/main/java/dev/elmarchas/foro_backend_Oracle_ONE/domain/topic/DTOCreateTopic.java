package dev.elmarchas.foro_backend_Oracle_ONE.domain.topic;

import dev.elmarchas.foro_backend_Oracle_ONE.domain.classroom.Classroom;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DTOCreateTopic(

        @NotBlank String title,
        @NotBlank String message,
        @NotNull Long userId,
        @NotNull Long classroomId) {

}
