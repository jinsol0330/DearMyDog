package sorzzzzy.dearmydog.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sorzzzzy.dearmydog.domain.Item.Item;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // 아이템을 처음 저장할 때는 id가 없기 때문
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    // 상품 단건 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    // 상품 리스트 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
