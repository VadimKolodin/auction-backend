package flowdata.user.cart;


import flowdata.user.inventory.InventoryEntityId;
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
@Table(name="casrt")
public class CartEntity {

    @EmbeddedId
    private InventoryEntityId id;

    @Column(name="amount")
    private Integer amount;
}