package com.kurdistan.mapper;

import com.kurdistan.dto.UserDTO;
import com.kurdistan.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { AddressMapper.class })
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO customerToCustomerDTO(User customer);

    User customerDTOToCustomer(UserDTO userDTO);
}

