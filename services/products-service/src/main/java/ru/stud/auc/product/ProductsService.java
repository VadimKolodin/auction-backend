package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;
import ru.stud.auc.exception.BadRequestException;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsInserter productsInserter;

    private final ProductsUpdater productsUpdater;

    public ProductView createProduct(ProductDto dto) {
        return productsInserter.createProduct(dto);
    }

    public void deleteProduct(UUID productId) {
        productsUpdater.deleteProduct(productId);
    }

    public void restoreProduct(UUID productId) {
        productsUpdater.restoreProduct(productId);
    }

    public List<ProductView> searchProducts(int maxResult, int offset, Optional<String> nameSearchString, Optional<Boolean> nameAsc, Optional<Boolean> costAsc, List<Tag> tags, List<SubTag> subTags) {
        if (nameAsc.isPresent() && costAsc.isPresent() && nameAsc.get() && costAsc.get()) {
            throw new BadRequestException("Не могут быть одновременно выбраны 2 параметра сортировки. NameAsc = true && CostAsc = true");
        }
        if (maxResult < 0) {
            throw new BadRequestException("Количество максимальных результатов не может быть меньше 0");
        }
        if (offset < 0) {
            throw new BadRequestException("Смещение не может быть меньше 0");
        }
        return productsInserter.searchProducts(maxResult, offset, nameSearchString, nameAsc, costAsc, tags, subTags);
    }
}
