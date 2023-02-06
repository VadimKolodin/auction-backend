package flowdata.frozencredit;

import common.SoftDeleteEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name="frozen_credit")
public class FrozenCredit extends SoftDeleteEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "auction_id")
    private UUID auctionId;

    @Column(name = "credit")
    private Integer credit;

}
