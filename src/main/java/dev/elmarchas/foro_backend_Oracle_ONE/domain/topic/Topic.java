package dev.elmarchas.foro_backend_Oracle_ONE.domain.topic;

import java.time.LocalDateTime;
import java.util.List;

import dev.elmarchas.foro_backend_Oracle_ONE.domain.reply.Reply;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.classroom.Classroom;
import dev.elmarchas.foro_backend_Oracle_ONE.domain.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime timeStamp;
    private int status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
    @OneToMany(mappedBy = "topics", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reply> replys;
    private Boolean active;

    public Topic(
            @NotBlank String title,
            @NotBlank String message,
            User user,
            Classroom classroom) {
        this.title = title;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
        this.status = 0;
        this.user = user;
        this.classroom = classroom;
        this.active = true;
    }
}
