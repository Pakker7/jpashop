package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;
    private String categoryName;

    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();


    // 카테고리가 1:N이 아니라 수없이 만들어 질 수 있기 때문에 카테고리 안에 카테고리를 계속 만들 수 있으니까 이렇게 만들어 준다.
    // 내 카테고리 위에 부모카테고리가 있을 수 있고(근데 1개만), 내 밑으로 자식 카테고리(다중 가능)가 있을 수 있으니 parent랑 child를 만들어줌
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") //parent_id는 뭐임
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
