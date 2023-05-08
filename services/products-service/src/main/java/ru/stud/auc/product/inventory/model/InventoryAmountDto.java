package ru.stud.auc.product.inventory.model;

import lombok.Data;
import ru.stud.auc.flowdata.product.inventory.InventoryEntityId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import java.util.UUID;

@Data
public class InventoryAmountDto {

    private UUID productId;

    private UUID sellerId;

    private Integer amount;

}
