package com.oegs.wpc.service.client;

import com.oegs.wpc.dto.client.ClientDTO;
import com.oegs.wpc.mapper.client.ClientMapper;
import com.oegs.wpc.model.client.Client;
import com.oegs.wpc.repository.client.ClientRepository;
import com.oegs.wpc.validation.client.ClientValidator;
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
        if (creationPreCondition(client)) {
            return clientMapper.modelToDto(clientRepository.save(client));
        }
        return null;
    }

    @Transactional
    public ClientDTO updateClient(UUID clientId, Client clientToUpdate) {
        if (updatePreCondition(clientToUpdate, clientId)) {
            clientToUpdate.setClientId(clientId);
            return clientMapper.modelToDto(clientRepository.save(clientToUpdate));
        }
        return null;
    }

    public void deleteClient(UUID clientId) {
        if (clientExistenceChecker(clientId)) {
            clientRepository.deleteById(clientId);
        }
    }

}