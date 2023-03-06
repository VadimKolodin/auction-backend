package main.java.ru.stud.auc;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.flowdata.product.model.ProductView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductService productService;

    @GetMapping("/search/name")
    public ResponseEntity<List<ProductView>> searchProductsByName(@RequestParam("name") String name){
        return ResponseEntity.ok(productService.searchProductsByName(name));
    }
    @GetMapping("/search/description")
    public ResponseEntity<List<ProductView>> searchProductsByDescription(@RequestParam("description") String description){
        return ResponseEntity.ok(productService.searchProductsByDescription(description));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductView>> searchProductsByNameOrDescription(@RequestParam("query") String query){
        return ResponseEntity.ok(productService.searchProductsByNameOrDescription(query));
    }
}
