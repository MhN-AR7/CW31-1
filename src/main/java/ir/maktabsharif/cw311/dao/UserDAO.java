package ir.maktabsharif.cw311.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
