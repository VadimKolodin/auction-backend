package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;
import ru.stud.auc.flowdata.product.model.ProductView;

import java.util.List;
import java.util.Optional;

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


    @Override
    public List<ProductView> searchProducts (
            int size,
            int page,
            Optional<String> nameSearchString,
            Optional<Boolean> nameAsc,
            Optional<Boolean> costAsc,
            List<Tag> tags,
            List<SubTag> subTags

    ) {

        return productsService.searchProducts(size, page, nameSearchString, nameAsc, costAsc, tags, subTags);
    }
}
