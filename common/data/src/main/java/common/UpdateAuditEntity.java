package common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
public class UpdateAuditEntity {

    @Column(name = "insert_user_id")
    private UUID insertUserId;

    @Column(name = "insert_dt")
    private LocalDateTime insertDatetime;

    @Column(name = "update_user_id")
    private UUID updateUserId;

    @Column(name = "update_dt")
    private LocalDateTime updateDatetime;


}
