package ru.stud.auc.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductsController implements ProductsApi {

    private final ProductsGetter productsGetter;

    private final ProductsService productsService;

    @Override
    public ProductView getProduct(UUID productId) {
        return productsGetter.getProduct(productId);
    }

    @Override
    public ResponseEntity<byte[]> getImageProduct(UUID productId) {
     byte[] bytes =  productsGetter.getProduct(productId).getImage();
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }


}
