package com.fiap.fastfood.adapters.persistence.orm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PersonORM {

    private String name;
    private LocalDate birthday;
    private String cpf;
    private String email;
    private LocalDateTime creationTimestamp;
    private LocalDateTime updateTimestamp;

}
