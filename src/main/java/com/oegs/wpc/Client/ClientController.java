package com.oegs.wpc.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    private List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("{clientId}")
    private Optional<Client> getClientById(UUID clientId){
        return clientService.getClientById(clientId);
    }

    @PostMapping(value = "/", produces = "application/json")
    public Client createNewClient(@RequestBody Client newClient){
        return clientService.createNewClient(newClient);
    }

    @PostMapping(path = "{clientId}")
    public void updateClient(@PathVariable("clientId") @RequestBody UUID clientId, Client client){
        clientService.updateClient(clientId, client);
    }
}
