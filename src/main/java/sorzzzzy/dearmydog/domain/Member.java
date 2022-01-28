package sorzzzzy.dearmydog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    @Enumerated(EnumType.STRING) // 임베디드 타입은 '@Enumerated' 이 꼭 필요함
    private Address address;

    // 연관관계의 주인이 아닌 쪽에는 'mappedBy' 사용
    // 여기서 member는 'Order 테이블에 있는 member 필드에 의해 mapped 됐다' 는 의미
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
