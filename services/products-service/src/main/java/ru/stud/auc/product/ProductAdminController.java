package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.flowdata.product.model.ProductAdminView;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import java.util.UUID;
@RestController
@RequiredArgsConstructor
public class ProductAdminController implements ProductsAdminApi {

    private final ProductsGetter productsGetter;

    private final ProductsService productsService;

    @Override
    public ProductAdminView getProduct(UUID productId) {
        return productsGetter.getProductWithDeleted(productId);
    }

    @Override
    public ProductView createProduct(ProductDto request) {
        return productsService.createProduct(request);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productsService.deleteProduct(productId);
    }

    @Override
    public void restoreProduct(UUID productId) {
        productsService.restoreProduct(productId);
    }

}
