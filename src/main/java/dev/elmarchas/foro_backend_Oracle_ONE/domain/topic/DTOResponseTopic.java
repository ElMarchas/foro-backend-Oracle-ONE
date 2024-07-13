package dev.elmarchas.foro_backend_Oracle_ONE.domain.topic;

import java.time.LocalDateTime;

public record DTOResponseTopic(
        Long id,
        String title,
        String message,
        LocalDateTime timeStamp,
        int status,
        Long userId,
        String userName,
        Long classroomId,
        String classroomName,
        Boolean active) {

}
