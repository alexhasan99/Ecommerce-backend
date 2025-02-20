package com.kurdistan.service.impl;

import com.kurdistan.db.dao.AddressDao;
import com.kurdistan.db.dao.UserDao;
import com.kurdistan.dto.AddressDTO;
import com.kurdistan.dto.UserDTO;
import com.kurdistan.mapper.AddressMapper;
import com.kurdistan.mapper.UserMapper;
import com.kurdistan.model.Address;
import com.kurdistan.model.User;
import com.kurdistan.service.interfaces.UserService;
import com.kurdistan.security.KeycloakAdminClient;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final AddressDao addressDao;
    private final KeycloakService keycloakService;

    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    public UserServiceImpl(UserDao userDao, AddressDao addressDao, KeycloakService keycloakService) {
        this.userDao = userDao;
        this.addressDao = addressDao;
        this.keycloakService = keycloakService;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        if (keycloakService.userExistsInKeycloak(userDTO.getEmail())) {
            throw new RuntimeException("Användaren med e-post " + userDTO.getEmail() + " finns redan.");
        }

        String userId = keycloakService.createUserInKeycloak(userDTO.getEmail(), userDTO.getFirstname(), userDTO.getLastname());
        keycloakService.setUserRole(userId, "user");
        keycloakService.setUserPassword(userId, userDTO.getPassword());

        User savedUser = saveUserInDatabase(userDTO, userId);
        return userMapper.userToUserDTO(savedUser);
    }


    private User saveUserInDatabase(UserDTO userDTO, String userId) {
        List<Address> savedAddresses = userDTO.getAddresses().stream()
                .map(addressMapper::addressDTOToAddress)
                .map(addressDao::save)
                .toList();

        User user = userMapper.userDTOToUser(userDTO);
        user.setId(userId);
        user.setAddresses(savedAddresses);

        return userDao.save(user);
    }


    @Override
    public Optional<UserDTO> getUserById(String id) {
        return userDao.findById(id)
                .map(userMapper::userToUserDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDao.findAll().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        user.setId(id);
        user = userDao.save(user);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteById(id);
    }

}