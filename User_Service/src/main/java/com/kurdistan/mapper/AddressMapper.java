package com.kurdistan.mapper;

import com.kurdistan.dto.AddressDTO;
import com.kurdistan.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO addressToAddressDTO(com.kurdistan.model.Address address);

    Address addressDTOToAddress(AddressDTO addressDTO);

}
