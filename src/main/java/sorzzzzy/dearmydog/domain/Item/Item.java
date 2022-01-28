package sorzzzzy.dearmydog.domain.Item;

import lombok.Getter;
import lombok.Setter;
import sorzzzzy.dearmydog.domain.Category;
import sorzzzzy.dearmydog.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// 상속관계 지정 - 싱글테이블 전략
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// dtype 으로 분류
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // ====비즈니스 로직====
    // 재고 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // 재고 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
