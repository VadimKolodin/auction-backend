package flowdata.user.inventory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
public class InventoryEntityId implements Serializable {

    @Column(name="product_id")
    private UUID productId;

    @Column(name="client_id")
    private UUID clientId;

}
