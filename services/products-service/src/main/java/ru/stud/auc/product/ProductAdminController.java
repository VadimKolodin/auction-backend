package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.annotation.hasroles.HasRole;
import ru.stud.auc.flowdata.product.model.ProductAdminView;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import java.util.UUID;
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductAdminController implements ProductsAdminApi {

    private final ProductsGetter productsGetter;

    private final ProductsService productsService;

    @Override
    @HasRole("ADMIN")
    public ProductAdminView getProduct(UUID productId) {
        return productsGetter.getProductWithDeleted(productId);
    }

    @Override
    @HasRole("ADMIN")
    public ProductView createProduct(ProductDto request) {
        return productsService.createProduct(request);
    }

    @Override
    @HasRole("ADMIN")
    public void deleteProduct(UUID productId) {
        productsService.deleteProduct(productId);
    }

    @Override
    @HasRole("ADMIN")
    public void restoreProduct(UUID productId) {
        productsService.restoreProduct(productId);
    }

}
