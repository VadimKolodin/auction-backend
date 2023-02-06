package flowdata.bergain;

import common.SoftDeleteEntity;
import common.enums.BargainState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bargain")
public class BargainEntity extends SoftDeleteEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "price")
    private Integer price;


    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private BargainState state;
}
