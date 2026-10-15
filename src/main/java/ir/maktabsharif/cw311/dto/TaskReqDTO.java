package ir.maktabsharif.cw311.dto;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TaskReqDTO {
    private String title;

    private String description;

    private Instant createdAt;

    private UserReqDTO user;
}
