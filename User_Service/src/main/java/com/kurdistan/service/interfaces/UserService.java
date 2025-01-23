package com.kurdistan.service.interfaces;

import com.kurdistan.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Optional<UserDTO> getUserById(String id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(String id, UserDTO userDTO);
    void deleteUser(String id);
}
