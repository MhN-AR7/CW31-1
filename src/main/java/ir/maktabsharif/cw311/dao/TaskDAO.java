package ir.maktabsharif.cw311.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tasks")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TaskDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne
    private UserDAO user;

    @PrePersist
    protected void onCreate() {
        if(createdAt == null)
            this.createdAt = Instant.now();
    }
}
