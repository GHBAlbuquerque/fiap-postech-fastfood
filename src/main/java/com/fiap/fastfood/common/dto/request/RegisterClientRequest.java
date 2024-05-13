package com.fiap.fastfood.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RegisterClientRequest {

    private String name;
    private LocalDate birthday;
    private String cpf;
    private String email;
    private String password;
}
