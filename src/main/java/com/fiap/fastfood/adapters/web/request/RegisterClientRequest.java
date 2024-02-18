package com.fiap.fastfood.adapters.web.request;

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
}
