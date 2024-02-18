package com.fiap.fastfood.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String idProduct;
    private Integer quantity;
    private BigDecimal itemValue;

    public BigDecimal getTotalItemValue() {
        return itemValue.multiply(BigDecimal.valueOf(quantity));
    }
}