package com.fiap.fastfood.common.builders;

import com.fiap.fastfood.core.entity.Item;
import com.fiap.fastfood.external.orm.ItemORM;

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
