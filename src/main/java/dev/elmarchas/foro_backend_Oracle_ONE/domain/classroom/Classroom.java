package dev.elmarchas.foro_backend_Oracle_ONE.domain.classroom;

import java.time.LocalDateTime;
import java.util.List;

import dev.elmarchas.foro_backend_Oracle_ONE.domain.reply.Reply;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.topic.Topic;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Table(name = "classrooms")
@Entity(name = "Classroom")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ClassCategory category;
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topic> topics;

}
