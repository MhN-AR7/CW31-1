package ir.maktabsharif.cw311.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRespDTO {
    private Long id;

    private String username;
}
