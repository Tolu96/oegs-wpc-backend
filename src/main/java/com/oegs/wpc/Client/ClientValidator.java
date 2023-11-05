package com.oegs.wpc.Client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Component
@AllArgsConstructor
public class ClientValidator {

    private ClientRepository employeeRepository;

    public boolean creationPreCondition(Client client) {
        client.setClientId(UUID.randomUUID());
        List<Client> newClient = Collections.singletonList(client);
        return newClient.stream().anyMatch(Objects::nonNull);
    }

    public boolean updatedPreCondition(Client client) {
        List<Client> newClient = Collections.singletonList(client);
        return newClient.stream().anyMatch(Objects::nonNull);
    }

}
