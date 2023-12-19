package com.oegs.wpc.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ClientDTO {
    UUID clientId;
    String clientName;
    String address;
    Integer postcode;
    String city;
}