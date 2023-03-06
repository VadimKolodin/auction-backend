package main.java.ru.stud.auc;

import ru.stud.auc.flowdata.product.model.ProductView;

import java.util.List;

public interface ProductService {
    List<ProductView> searchProductsByNameOrDescription(String query);

    List<ProductView> searchProductsByName(String name);

    List<ProductView> searchProductsByDescription(String description);

}
