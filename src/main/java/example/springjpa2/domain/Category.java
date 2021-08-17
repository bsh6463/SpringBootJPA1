package example.springjpa2.domain;

import example.springjpa2.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    String name;

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
                joinColumns = @JoinColumn(name = "ITEM_ID"),
                inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private List<Item> items = new ArrayList<>();

    //계층구조
    //다대일
    @ManyToOne(fetch = FetchType.LAZY)  //CATEGORY_ID가 아니라 PARENT_ID가 들어가야함...??
    @JoinColumn(name = "PRENT_ID")
    private Category parent;

    //일대다
    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();


}
