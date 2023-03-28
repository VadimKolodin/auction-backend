package ru.stud.auc.product.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.auth.util.AuthenticationUtils;
import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SellerInventoryController  implements  SellerInventoryApi{

    private final SellerInventoryService sellerInventoryService;

    @Override
    public List<SellerProductsDto> getSellerInventory() {
        UUID sellerId = AuthenticationUtils.getUserId();
        return sellerInventoryService.getSellerInventory(sellerId);
    }
}
