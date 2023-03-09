package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsInserter productsInserter;

    @Transactional
    public ProductView createProduct(ProductDto dto) {
        return productsInserter.createProduct(dto);
    }

}
