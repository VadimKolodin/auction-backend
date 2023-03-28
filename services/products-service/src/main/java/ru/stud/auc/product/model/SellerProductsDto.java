package ru.stud.auc.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stud.auc.flowdata.product.inventory.model.SellerInventoryProductView;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerProductsDto {

    private UUID sellerId;
    private List<SellerInventoryProductView> products;
}
