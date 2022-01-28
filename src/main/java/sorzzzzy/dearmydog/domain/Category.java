package sorzzzzy.dearmydog.domain;

import lombok.Getter;
import lombok.Setter;
import sorzzzzy.dearmydog.domain.Item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    // 중간 테이블에 매핑 <중요>
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // ====양방향 연관관계 편의 메서드====
    // 양방향이더라도 위치는 컨트롤 하는 쪽에서 작성하는 것이 좋음
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
