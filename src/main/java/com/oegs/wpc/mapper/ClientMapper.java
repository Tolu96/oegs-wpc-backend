package com.oegs.wpc.mapper;

import com.oegs.wpc.dto.ClientDTO;
import com.oegs.wpc.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UUID.class)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO modelToDto(Client client);

    List<ClientDTO> modelsToDtos(List<Client> client);

    Client dtoToModel(ClientDTO clientDTO);
}
