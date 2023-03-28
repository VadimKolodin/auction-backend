package ru.stud.auc.flowdata.product.cart;


import ru.stud.auc.flowdata.product.inventory.InventoryEntityId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name="products_carts")
public class CartEntity {

    @EmbeddedId
    private CartEntityId id;

    @Column(name="amount")
    private Integer amount;
}