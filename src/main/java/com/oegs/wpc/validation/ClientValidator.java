package com.oegs.wpc.validation;

import com.oegs.wpc.model.Client;
import com.oegs.wpc.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Component
public class ClientValidator {

    public final String CLIENT_NOT_FOUND_MESSAGE =
            "Client with this ID does not exist and therefore could not be " + "found";
    public final String WRONG_PERMISSION = "You do not have the right permissions to execute this action";
    public final String UPDATE_NOT_POSSIBLE = "Update was not possible. Please try it again later";
    public final String FILL_ALL_FIELDS = "Please fill all fields";
    public final String CLIENT_NAME_TOO_SHORT = "Client name is too short";
    public final String POSTCODE_INVALID = "Length of postcode is not valid";

    @Autowired
    private ClientRepository clientRepository;

    public boolean creationPreCondition(Client client) {
        fieldLengthChecker(client);
        return true;
    }

    public boolean updatePreCondition(Client client, UUID clientId) {
        if (clientExistenceChecker(clientId)) {
            fieldLengthChecker(client);
            return true;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, UPDATE_NOT_POSSIBLE);
    }

    private void fieldLengthChecker(Client client) {
        if (client.getClientName().length() <= 10) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, CLIENT_NAME_TOO_SHORT);
        }
        if (String.valueOf(client.getPostcode()).length() != 5) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, POSTCODE_INVALID);
        }
    }

    public boolean clientExistenceChecker(UUID clientId) {
        boolean exists = clientRepository.existsById(clientId);
        if (exists) {
            return true;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, CLIENT_NOT_FOUND_MESSAGE);
    }
}
