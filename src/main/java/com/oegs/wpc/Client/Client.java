package com.oegs.wpc.Client;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID clientId;
    private String clientName;
    private String address;
    private int postCode;
    private String town;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

public Client(UUID clientId, String clientName, String address, int postCode, String town, LocalDateTime createdAt,
              LocalDateTime updatedAt){

        this.clientId = clientId;
        this.clientName = clientName;
        this.address = address;
        this.postCode = postCode;
        this.town = town;
        this.updatedAt = updatedAt;
    }
}
