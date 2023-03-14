package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;
import ru.stud.auc.exception.BadRequestException;
import ru.stud.auc.flowdata.product.ProductEntity;
import ru.stud.auc.flowdata.product.ProductsRepository;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductsInserter {

    private final ProductsMapper productsMapper;

    private final ProductsRepository productsRepository;

    @Transactional
    public ProductView createProduct(ProductDto productDto) {
        ProductEntity product = productsMapper.toEntity(productDto);
        return productsMapper.toView(productsRepository.persist(product));
    }

    @Transactional
    public List<ProductView> searchProducts(int maxResult,
                                            int offset,
                                            Optional<String> nameSearchString,
                                            Optional<Boolean> nameAsc,
                                            Optional<Boolean> costAsc,
                                            List<Tag> tags,
                                            List<SubTag> subTags) {

        return productsRepository.searchProductsByName(maxResult, offset, nameSearchString, nameAsc, costAsc, tags, subTags);
    }
}
