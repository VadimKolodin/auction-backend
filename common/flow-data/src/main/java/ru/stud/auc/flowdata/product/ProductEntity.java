package ru.stud.auc.flowdata.product;

import org.hibernate.annotations.Type;
import ru.stud.auc.common.SoftDeleteEntity;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "products")
public class ProductEntity extends SoftDeleteEntity {

    @Id
    @Column(name="id")
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "image")
    private byte[] image;

    @Enumerated(EnumType.STRING)
    @Column(name="tag")
    private Tag tag;

    @Enumerated(EnumType.STRING)
    @Column(name="subTag")
    private SubTag subTag;

    @Column(name= "cost")
    private Integer cost;

    @Column(name= "min_cost")
    private Integer minCost;
}
