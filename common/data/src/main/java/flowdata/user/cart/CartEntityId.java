package flowdata.user.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
public class CartEntityId implements Serializable {

    @Column(name="product_id")
    private UUID productId;

    @Column(name="seller_id")
    private UUID sellerId;

}