package flowdata.auction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "auction_history")
public class AuctionHistoryEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "auction_id")
    private UUID auctionId;

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "request_date")
    private LocalDateTime requestDate;
}
