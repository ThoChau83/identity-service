package org.example.identityservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    boolean authenticated;
}
