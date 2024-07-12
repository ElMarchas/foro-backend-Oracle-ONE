package dev.elmarchas.foro_backend_Oracle_ONE.domain.topic;

import jakarta.validation.constraints.NotNull;

public record DTOUpdateTopic(@NotNull Long id, String nombre, String documento) {

}
