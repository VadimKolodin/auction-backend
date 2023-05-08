package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.consts.StringConsts;
import ru.stud.auc.exception.BadRequestException;
import ru.stud.auc.flowdata.product.ProductsRepository;
import ru.stud.auc.product.model.ProductDto;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductsUpdater {

    private final ProductsRepository productsRepository;

    @Transactional
    public void updateImage(UUID productId, byte[] image) {
        productsRepository.updateProductImage(productId, image);
    }

    @Transactional
    public void deleteProduct(UUID productId) {
        int changedProducts = productsRepository.setIsDeleted(productId, true);
        if (changedProducts < 1) {
            throw new BadRequestException(StringConsts.Product.NOT_FOUND);
        }
    }

    @Transactional
    public void restoreProduct(UUID productId) {
        int changedProducts = productsRepository.setIsDeleted(productId, false);
        if (changedProducts < 1) {
            throw new BadRequestException(StringConsts.Product.NOT_FOUND);
        }
    }

    @Transactional
    public void updateProduct(UUID productId, ProductDto productDto) {
        productsRepository.updateProduct(productId,
                                         productDto.getName(),
                                         productDto.getDescription(),
                                         productDto.getTag(),
                                         productDto.getSubTag(),
                                         productDto.getCost());
    }
}
