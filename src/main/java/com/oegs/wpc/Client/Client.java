package com.oegs.wpc.Client;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String clientName;
    @NotNull
    private String address;
    @NotNull
    private int postCode;
    @NotNull
    private String town;
    @NotNull
    private final LocalDateTime createdAt = LocalDateTime.now();
    @NotNull
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
