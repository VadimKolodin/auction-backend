package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;
import ru.stud.auc.consts.StringConsts;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.product.ProductEntity;
import ru.stud.auc.flowdata.product.ProductsRepository;
import ru.stud.auc.flowdata.product.model.ProductAdminView;
import ru.stud.auc.flowdata.product.model.ProductView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductsGetter {

    private final ProductsMapper productsMapper;

    private final ProductsRepository productsRepository;

    public ProductAdminView getProductWithDeleted(UUID productId) {
        ProductEntity product = productsRepository.findById(productId)
                                                  .orElseThrow(() -> new NotFoundException(StringConsts.Product.NOT_FOUND));
        return productsMapper.toAdminView(product);
    }

    public ProductView getProduct(UUID productId) {
        ProductEntity product = productsRepository.findById(productId)
                                                  .orElseThrow(() -> new NotFoundException(StringConsts.Product.NOT_FOUND));
        if (product.getIsDeleted()) {
            throw new NotFoundException(StringConsts.Product.NOT_FOUND);
        }
        return productsMapper.toView(product);
    }

    public void checkExistsAndNotDeleted(UUID productId) {
        ProductEntity productEntity = productsRepository.findById(productId).orElseThrow(() -> new NotFoundException(StringConsts.Product.NOT_FOUND));
        if (productEntity.getIsDeleted()) {
            throw new NotFoundException(StringConsts.Product.NOT_FOR_SALE);
        }
    }

    public List<ProductView> getAllCurrentProducts() {
        return productsRepository.getAllCurrentProducts();
    }

    public  List<ProductView> searchProductByName(int pageSize, int page, Optional<String> nameSearchString, Optional<Boolean> nameAsc, Optional<Boolean> costAsc, List<Tag> tags, List<SubTag> subTags) {
        int offset = page * pageSize;
        return productsRepository.searchProductsByName(pageSize, offset, nameSearchString, nameAsc, costAsc, tags, subTags);
    }



}
