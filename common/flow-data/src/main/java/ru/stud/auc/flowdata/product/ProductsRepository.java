package ru.stud.auc.flowdata.product;

import org.springframework.stereotype.Repository;
import ru.stud.auc.common.SoftDeleteAuditRepository;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;
import ru.stud.auc.flowdata.product.model.ProductView;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductsRepository extends SoftDeleteAuditRepository<ProductEntity> {

    public int setIsDeleted(UUID productId, Boolean isDeleted) {
        String q = "update ProductEntity p set p.isDeleted = :isDeleted where p.id = :productId";
        return em.createQuery(q)
          .setParameter("productId", productId)
          .setParameter("isDeleted", isDeleted)
                .executeUpdate();
    }

    public List<ProductView> getAllCurrentProducts() {
        String q = """
                   select new ru.stud.auc.flowdata.product.model.ProductView(
                   p.id,
                   p.name,
                   p.description,
                   p.image,
                   p.tag,
                   p.subTag,
                   p.cost
                   ) from ProductEntity p 
                   where p.isDeleted = false 
                   """;
        return em.createQuery(q, ProductView.class).getResultList();
    }

    public List<ProductView> searchProductsByName(String name){
        String q = """
                  select new ru.stud.auc.flowdata.product.model.ProductView(
                   p.id,
                   p.name,
                   p.description,
                   p.image,
                   p.tag,
                   p.subTag,
                   p.cost
                   ) from ProductEntity p 
                   where p.isDeleted = false  and
                   p.name LIKE CONCAT ('%', :name, '%')
                   """;

        return em.createQuery(q, ProductView.class)
                .setParameter("name", name)
                .getResultList();

    }

    public List<ProductView> searchProductsByDescription(String description){
        String q = """
                  select new ru.stud.auc.flowdata.product.model.ProductView(
                   p.id,
                   p.name,
                   p.description,
                   p.image,
                   p.tag,
                   p.subTag,
                   p.cost
                   ) from ProductEntity p 
                   where p.isDeleted = false  and
                   p.description LIKE CONCAT ('%', :description, '%')
                   """;

        return em.createQuery(q, ProductView.class)
                .setParameter("description", description)
                .getResultList();

    }

    public List<ProductView> searchProductsByNameOrDescription(String query){
        String q = """
                  select new ru.stud.auc.flowdata.product.model.ProductView(
                   p.id,
                   p.name,
                   p.description,
                   p.image,
                   p.tag,
                   p.subTag,
                   p.cost
                   ) from ProductEntity p 
                   where p.isDeleted = false  and
                   p.name LIKE CONCAT ('%', :query, '%') or
                   p.description LIKE CONCAT('%', :query, '%') 
                   """;

        return em.createQuery(q, ProductView.class)
                .setParameter("query", query)
                .getResultList();

    }

    public void updateProduct(UUID productId,
                              String name,
                              String description,
                              Tag tag,
                              SubTag subTag,
                              Integer cost) {
        String q = """
                   update ProductEntity p
                   set 
                   p.name = :name,
                   p.description = :description,
                   p.tag = :tag,
                   p.subTag = :subTag,
                   p.cost = :cost
                   where p.id = :productId
                   """;
        em.createQuery(q)
          .setParameter("productId", productId)
          .setParameter("name", name)
          .setParameter("description", description)
          .setParameter("tag", tag)
          .setParameter("subTag", subTag)
          .setParameter("cost", cost)
          .executeUpdate();
    }
}
