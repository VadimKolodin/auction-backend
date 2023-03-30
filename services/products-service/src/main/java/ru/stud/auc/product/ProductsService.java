package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;
import ru.stud.auc.exception.BadRequestException;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
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

    public void uploadPhoto(UUID productId, byte[] image) {
        productsUpdater.updateImage(productId, image);
    }
    @Transactional
    public List<ProductView> searchProducts(int size,
                                            int page,
                                            Optional<String> nameSearchString,
                                            Optional<Boolean> nameAsc,
                                            Optional<Boolean> costAsc,
                                            List<Tag> tags,
                                            List<SubTag> subTags) {

        if (nameAsc.orElse(false) && costAsc.orElse(false)) {
            throw new BadRequestException("Не могут быть одновременно выбраны 2 параметра сортировки. NameAsc = true && CostAsc = true");
        }
        int offset = page * size;
        return productsInserter.searchProducts(size, offset, nameSearchString, nameAsc, costAsc, tags, subTags);
    }
}
