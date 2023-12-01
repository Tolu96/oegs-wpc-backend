package com.oegs.wpc.mapper.client;

import com.oegs.wpc.dto.client.ClientDTO;
import com.oegs.wpc.model.client.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UUID.class)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO modelToDto(Client client);

    List<ClientDTO> modelsToDtos(List<Client> client);

    Client dtoToModel(ClientDTO clientDTO);
}
