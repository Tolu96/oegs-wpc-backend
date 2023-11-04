package com.oegs.wpc.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientValidator clientValidator;

    public List<Client> getAllClients() {
        return new ArrayList<>(clientRepository.findAll().stream().toList());
    }

    public Optional<Client> getClientById(UUID clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Client with ID " + clientId + " does not exist");
        }else{
            return clientRepository.findById(clientId);
        }
    }

    public Client createNewClient(Client newClient){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(clientRepository.existsById(newClient.getClientId())){
            newClient.getCreatedAt().format(dtf);
            newClient.getUpdatedAt().format(dtf);
            return clientRepository.save(newClient);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Please fill all fields");
    }

    @Transactional
    public void updateClient(UUID clientId, Client updatedClient){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(!clientValidator.updatedPreCondition(updatedClient)){
            Client oldClient =
                    (clientRepository.findById(clientId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Client does not exist")));
            oldClient.setUpdatedAt(LocalDateTime.now());
            oldClient.getUpdatedAt().format(dtf);
        }
    }
}
