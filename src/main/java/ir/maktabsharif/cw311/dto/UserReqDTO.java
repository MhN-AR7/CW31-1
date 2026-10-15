package ir.maktabsharif.cw311.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserReqDTO {
    private String username;

    private String password;
}
