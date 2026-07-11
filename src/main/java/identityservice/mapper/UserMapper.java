package identityservice.mapper;

import identityservice.dto.request.UserCreationRequest;
import identityservice.dto.request.UserUpdateRequest;
import identityservice.dto.response.UserResponse;
import identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    UserResponse toUserResponse(User user);
}
