package dev.elmarchas.foro_backend_Oracle_ONE.domain.topic;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotBlank;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByActiveTrue(Pageable paginacion);

    Optional<Topic> findByTitle(@NotBlank String title);

    Optional<Topic> findByMessage(@NotBlank String message);
}
