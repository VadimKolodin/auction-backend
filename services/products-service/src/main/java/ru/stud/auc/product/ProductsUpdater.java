package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.ProductsRepository;
import ru.stud.auc.product.model.ProductDto;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductsUpdater {

    private final ProductsRepository productsRepository;

    @Transactional
    public void resotreProduct(UUID productId) {
        productsRepository.setIsDeleted(productId, false);
    }

    @Transactional
    public void updateProduct(UUID productId, ProductDto productDto) {
        productsRepository.updateProduct(productId,
                                         productDto.getName(),
                                         productDto.getDescription(),
                                         productDto.getImage(),
                                         productDto.getTag(),
                                         productDto.getSubTag(),
                                         productDto.getCost());
    }
}
