package com.oegs.wpc.controller;

import com.oegs.wpc.dto.ClientDTO;
import com.oegs.wpc.model.Client;
import com.oegs.wpc.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/client")
public class ClientController {

    private ClientService clientService;

    @GetMapping
    private ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @GetMapping("{clientId}")
    private ResponseEntity<Optional<ClientDTO>> getClientById(@PathVariable("clientId") UUID clientId) {
        return ResponseEntity.ok().body(clientService.getClientById(clientId));
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<ClientDTO> createNewClient(@RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.createNewClient(clientDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "{clientId}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable("clientId") UUID clientId, @RequestBody Client client) {
        ClientDTO updatedClient = clientService.updateClient(clientId, client);
        return new ResponseEntity<>(updatedClient, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable("clientId") UUID clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>("Client with the Id" + clientId + " successfully deleted", HttpStatus.OK);
    }
}