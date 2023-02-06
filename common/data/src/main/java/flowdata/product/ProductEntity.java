package flowdata.product;

import common.SoftDeleteEntity;
import common.enums.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class ProductEntity extends SoftDeleteEntity {

    @Id
    @Column(name="id")
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Lob
    @Column(name = "image")
    private Byte[] image;

    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private Category category;

    @Column(name= "cost")
    private Integer cost;
}
