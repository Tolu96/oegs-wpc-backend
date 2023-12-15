package com.oegs.wpc.service;

import com.oegs.wpc.dto.ClientDTO;
import com.oegs.wpc.mapper.ClientMapper;
import com.oegs.wpc.model.Client;
import com.oegs.wpc.repository.ClientRepository;
import com.oegs.wpc.validation.ClientValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService extends ClientValidator {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public List<ClientDTO> getAllClients() {
        return clientMapper.modelsToDtos(clientRepository.findAll());
    }

    public Optional<ClientDTO> getClientById(UUID clientId) {
        if (clientExistenceChecker(clientId)) {
            return Optional.ofNullable(clientMapper.modelToDto(clientRepository.findById(clientId).orElseThrow()));
        }
        return Optional.empty();
    }

    @Transactional
    public ClientDTO createNewClient(ClientDTO clientDTO) {
        Client client = clientMapper.dtoToModel(clientDTO);
        creationPreCondition(client);
        return clientMapper.modelToDto(clientRepository.save(client));
    }

    @Transactional
    public ClientDTO updateClient(UUID clientId, Client clientToUpdate) {
        updatePreCondition(clientToUpdate, clientId);
        clientToUpdate.setClientId(clientId);
        return clientMapper.modelToDto(clientRepository.save(clientToUpdate));

    }

    public void deleteClient(UUID clientId) {
        if (clientExistenceChecker(clientId)) {
            clientRepository.deleteById(clientId);
        }
    }

}
