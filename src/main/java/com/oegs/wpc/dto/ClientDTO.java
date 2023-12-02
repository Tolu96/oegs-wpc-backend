package com.oegs.wpc.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClientDTO {
    String clientName;
    String address;
    Integer postcode;
    String city;
}