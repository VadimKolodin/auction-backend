package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.stud.auc.exception.BadRequestException;
import ru.stud.auc.exception.InternalException;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.product.model.ProductAdminView;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
public class ProductAdminController implements ProductsAdminApi {

    private final ProductsGetter productsGetter;

    private final ProductsService productsService;

    @Override
    public ProductAdminView getProduct(UUID productId) {
        return productsGetter.getProductWithDeleted(productId);
    }

    @Override
    public ProductView createProduct(ProductDto request) {
        return productsService.createProduct(request);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productsService.deleteProduct(productId);
    }

    @Override
    public void restoreProduct(UUID productId) {
        productsService.restoreProduct(productId);
    }

    @Override
    public void uploadProduct(UUID productId, MultipartFile multipartFile) {
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
            multipartFile.transferTo(convFile);
            productsService.uploadPhoto(productId, IOUtils.toByteArray(new FileInputStream(convFile)));
        } catch(IOException e) {
            throw new InternalException("Не удалось загрузить файл: " + e.getMessage());
        }
    }



}
