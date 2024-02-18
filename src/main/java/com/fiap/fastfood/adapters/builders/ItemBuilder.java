package com.fiap.fastfood.adapters.builders;

import com.fiap.fastfood.adapters.persistence.orm.ItemORM;
import com.fiap.fastfood.application.domain.Item;

public class ItemBuilder {

    public static Item fromOrmToDomain(ItemORM orm) {
        return new Item()
                .setItemValue(orm.getItemValue())
                .setIdProduct(orm.getIdProduct())
                .setQuantity(orm.getQuantity());
    }

    public static ItemORM fromDomainToOrm(Item item) {
        return new ItemORM()
                .setItemValue(item.getItemValue())
                .setIdProduct(item.getIdProduct())
                .setQuantity(item.getQuantity());
    }
}
