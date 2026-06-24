package org.example.identityservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Builder
@Data
@NoArgsConstructor       // <--- THÊM DÒNG NÀY (Để Jackson đọc được JSON)
@AllArgsConstructor      // <--- THÊM DÒNG NÀY (Để Builder hoạt động)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    String username;
    String password;
}
