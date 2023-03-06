package main.java.ru.stud.auc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.flowdata.product.ProductsRepository;
import ru.stud.auc.flowdata.product.model.ProductView;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductsRepository productRepository;

    @Override
    public List<ProductView> searchProductsByNameOrDescription(String query) {
        return productRepository.searchProductsByNameOrDescription(query);
    }

    @Override
    public List<ProductView> searchProductsByName(String name) {
        return productRepository.searchProductsByName(name);
    }

    @Override
    public List<ProductView> searchProductsByDescription(String description) {
        return productRepository.searchProductsByDescription(description);
    }


}
