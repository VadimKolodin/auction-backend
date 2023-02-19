package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.ProductEntity;
import ru.stud.auc.flowdata.product.ProductsRepository;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import javax.transaction.Transactional;

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
}
