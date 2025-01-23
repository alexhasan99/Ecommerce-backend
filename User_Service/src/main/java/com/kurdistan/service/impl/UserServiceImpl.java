package com.kurdistan.service.impl;

import com.kurdistan.db.dao.UserDao;
import com.kurdistan.dto.UserDTO;
import com.kurdistan.mapper.UserMapper;
import com.kurdistan.model.User;
import com.kurdistan.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.customerDTOToCustomer(userDTO);
        user = userDao.save(user);
        return userMapper.customerToCustomerDTO(user);
    }

    @Override
    public Optional<UserDTO> getUserById(String id) {
        return userDao.findById(id)
                .map(userMapper::customerToCustomerDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDao.findAll().stream()
                .map(userMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) {
        User user = userMapper.customerDTOToCustomer(userDTO);
        user.setId(id);
        user = userDao.save(user);
        return userMapper.customerToCustomerDTO(user);
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteById(id);
    }

}