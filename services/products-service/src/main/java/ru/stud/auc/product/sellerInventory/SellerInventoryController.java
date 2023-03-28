package ru.stud.auc.product.sellerInventory;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SellerInventoryController  implements  SellerInventoryApi{

    private final SellerInventoryService sellerInventoryService;

    @Override
    public SellerProductsDto getSellerInventory(UUID sellerId) {

        return sellerInventoryService.getSellerInventory(sellerId);
    }
}
