package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.ProductsRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductsDeleter {

    private final ProductsRepository productsRepository;

    @Transactional
    public void deleteProduct(UUID productId) {
        productsRepository.setIsDeleted(productId, true);
    }
}
