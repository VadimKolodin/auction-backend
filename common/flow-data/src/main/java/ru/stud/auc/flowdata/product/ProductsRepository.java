package ru.stud.auc.flowdata.product;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.stud.auc.common.SoftDeleteAuditRepository;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;
import ru.stud.auc.flowdata.product.model.ProductView;

import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class ProductsRepository extends SoftDeleteAuditRepository<ProductEntity> {

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

    public List<ProductView> searchProductsByName(int maxResult, int offset, Optional<String> nameSearchString, Optional<Boolean> nameAsc, Optional<Boolean> costAsc, List<Tag> tags, List<SubTag> subTags){
        StringBuilder q = new StringBuilder("""
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
                   """);
        Map<String, Object> parameters = new HashMap<>();
        if (nameSearchString.isPresent()) {
            q.append(" and p.name like :searchString ");
            parameters.put("searchString", '%'+nameSearchString.get()+'%');
        }
        if (CollectionUtils.isEmpty(tags)){
            q.append(" and p.tag in :tags ");
            parameters.put("tags", tags);
        }
        if (CollectionUtils.isEmpty(subTags)){
            q.append(" and p.subTag in :subTags ");
            parameters.put("subTags", subTags);
        }
        if (nameAsc.isPresent()){
            q.append("order by p.name").append(nameAsc.get() ? " asc " : " desc ");
        } else if (costAsc.isPresent()) {
            q.append("order by p.cost").append(costAsc.get() ? " asc " : " desc ");
        }

        TypedQuery<ProductView> typedQuery =  em.createQuery(q.toString(), ProductView.class);
        for (Map.Entry<String, Object> param: parameters.entrySet()) {
            typedQuery.setParameter(param.getKey(), param.getValue());
        }
        return typedQuery
                .setMaxResults(maxResult)
                .setFirstResult(offset)
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

    public int setIsDeleted(UUID productId, Boolean isDeleted) {
        String q = "update ProductEntity p set p.isDeleted = :isDeleted where p.id = :productId";
        return em.createQuery(q)
                 .setParameter("productId", productId)
                 .setParameter("isDeleted", isDeleted)
                 .executeUpdate();
    }
}
