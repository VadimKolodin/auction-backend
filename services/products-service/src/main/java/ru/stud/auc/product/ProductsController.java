package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

@RestController
@RequiredArgsConstructor
public class ProductsController implements ProductsApi {

    private final ProductsService productsService;

    @Override
    public ProductView create(ProductDto request) {
        return productsService.createProduct(request);
    }
}
