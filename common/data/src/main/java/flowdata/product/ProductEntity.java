package flowdata.product;

import common.SoftDeleteEntity;
import common.enums.SubTag;
import common.enums.Tag;
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

    //@Lob
    //@Column(name = "image")
    //private Byte[] image;

    @Enumerated(EnumType.STRING)
    @Column(name="tag")
    private Tag tag;

    @Enumerated(EnumType.STRING)
    @Column(name="subTag")
    private SubTag subTag;

    @Column(name= "cost")
    private Integer cost;
}
