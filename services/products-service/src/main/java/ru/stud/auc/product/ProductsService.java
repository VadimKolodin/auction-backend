package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.stud.auc.flowdata.product.ProductEntity;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;
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

    public void uploadPhoto(UUID productId, MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipartFile.transferTo(convFile);

        productsUpdater.setImage(productId, IOUtils.toByteArray(new FileInputStream(convFile)));
    }
}
