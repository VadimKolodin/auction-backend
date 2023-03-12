package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsInserter productsInserter;

    private final ProductsUpdater productsUpdater;

    public ProductView createProduct(ProductDto dto) {
        return productsInserter.createProduct(dto);
    }

    public void deleteProduct(UUID productId) {
        productsUpdater.deleteProduct(productId);
    }

    public void restoreProduct(UUID productId) {
        productsUpdater.restoreProduct(productId);
    }
}
