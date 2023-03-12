package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductsController implements ProductsApi {

    private final ProductsGetter productsGetter;

    private final ProductsService productsService;

    @Override
    public ProductView getProduct(UUID productId) {
        return productsGetter.getProduct(productId);
    }
}
