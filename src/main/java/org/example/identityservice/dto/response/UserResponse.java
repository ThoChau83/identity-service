package org.example.identityservice.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String username;
    String firstName;
    String lastName;
    LocalDate dob;

}
