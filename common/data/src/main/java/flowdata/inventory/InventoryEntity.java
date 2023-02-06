package flowdata.inventory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="inventory")
public class InventoryEntity {

    @EmbeddedId
    private InventoryEntityId id;

    @Column(name="amount")
    private Integer amount;
}
