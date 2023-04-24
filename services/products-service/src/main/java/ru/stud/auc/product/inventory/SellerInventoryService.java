package ru.stud.auc.product.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stud.auc.auth.util.AuthenticationUtils;
import ru.stud.auc.flowdata.product.inventory.model.InventoryProductView;
import ru.stud.auc.product.ProductsGetter;
import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerInventoryService {

    private final ProductsGetter productsGetter;

    private final InventoryMapper inventoryMapper;

    private final InventoryGetter inventoryGetter;
    
    private final InventoryInserter inventoryInserter;

    private final InventoryUpdater inventoryUpdater;

    private final InventoryDeleter inventoryDeleter;

    public List<SellerProductsDto> getSellerInventory(UUID sellerId) {
        return inventoryGetter.getSellerInventory(sellerId).stream().map(inventoryMapper::toInventoryDto).toList();
    }

 

    public void addToInventory(UUID productId) {
        productsGetter.checkExistsAndNotDeleted(productId);
        UUID userId = AuthenticationUtils.getUserId();
        inventoryInserter.addInventory(productId, userId);
    }

    public void setInventoryAmount(UUID productId, int amount) {
        productsGetter.checkExistsAndNotDeleted(productId);
        List<InventoryProductView> inventoryProductViewList = inventoryGetter.getSellerInventory(AuthenticationUtils.getUserId());

        if (isExists(inventoryProductViewList, productId)) {
            if (amount == 0){
                deleteInventoryProduct(productId);
            }else {
                inventoryUpdater.setAmount(productId, AuthenticationUtils.getUserId(), amount);
            }
        } else {
            addToInventory(productId);
        }
    }
    
    public void deleteInventory() {
        inventoryDeleter.deleteAllInventory(AuthenticationUtils.getUserId());
    }

    public void deleteInventoryProduct(UUID productId){
        productsGetter.checkExistsAndNotDeleted(productId);
        inventoryDeleter.deleteInventoryProduct(productId, AuthenticationUtils.getUserId());
    }
    
    private boolean isExists(List<InventoryProductView> inventoryProductViewList, UUID productID){
        for (InventoryProductView view:inventoryProductViewList) {
            if (view.getProductId().equals(productID)){
                return true;
            }
        }
        return false;
    }
}
