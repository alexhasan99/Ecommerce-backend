package com.kurdistan.db.dao;

import com.kurdistan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {
    List<User> findByAddresses_Id(Long addressId);
    Optional<User> findByEmail(String email);

}
