package ir.maktabsharif.cw311.dto;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TaskRespDTO {
    private Long id;

    private String title;

    private String description;

    private Instant createdAt;
}
