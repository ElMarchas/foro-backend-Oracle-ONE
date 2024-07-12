package dev.elmarchas.foro_backend_Oracle_ONE.domain.topic;

import java.time.LocalDateTime;

public record DTOListTopic(
        Long id,
        String title,
        String message,
        LocalDateTime timeStamp,
        int status,
        Long userId,
        Long classroomI) {

    public DTOListTopic(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getTimeStamp(),
                topic.getStatus(),
                topic.getUser().getId(),
                topic.getClassroom().getId());
    }
}
