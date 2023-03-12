package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.consts.StringConsts;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.product.ProductEntity;
import ru.stud.auc.flowdata.product.ProductsRepository;
import ru.stud.auc.flowdata.product.model.ProductAdminView;
import ru.stud.auc.flowdata.product.model.ProductView;

import java.util.List;
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

    public List<ProductView> getAllCurrentProducts() {
        return productsRepository.getAllCurrentProducts();
    }

    public  List<ProductView> searchProductByName(String name) { return productsRepository.searchProductsByName(name); }

    public  List<ProductView> searchProductByDescription(String description)
    { return productsRepository.searchProductsByDescription(description); }

    public  List<ProductView> searchProductByNameOrDescription(String query)
    { return productsRepository.searchProductsByNameOrDescription(query); }

}
