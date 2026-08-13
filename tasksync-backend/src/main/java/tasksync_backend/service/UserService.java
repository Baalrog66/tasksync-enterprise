package tasksync_backend.service;

import tasksync_backend.dto.UserRequestDto;
import tasksync_backend.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(String id);
    void deleteUser(String id);
}