package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;
import ru.stud.auc.exception.BadRequestException;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductsController implements ProductsApi {

    private final ProductsService productsService;

    @Override
    public ProductView create(ProductDto request) {
        return productsService.createProduct(request);
    }


    @Override
    public List<ProductView> searchProducts (
            int maxResult,
            int offset,
            Optional<String> nameSearchString,
            Optional<Boolean> nameAsc,
            Optional<Boolean> costAsc,
            List<Tag> tags,
            List<SubTag> subTags

    ) {

        return productsService.searchProducts(maxResult, offset, nameSearchString, nameAsc, costAsc, tags, subTags);
    }
}
