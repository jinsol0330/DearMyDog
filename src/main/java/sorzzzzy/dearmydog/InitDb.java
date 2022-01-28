package sorzzzzy.dearmydog;

import sorzzzzy.dearmydog.domain.*;
import sorzzzzy.dearmydog.domain.Item.Food;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 총 주문 2개
 * * userA
 * 	 * Dog Meal
 * 	 * Dog Snack
 * * userB
 * 	 * Cat Meal
 * 	 * Cat Snack
 */

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            System.out.println("Init1" + this.getClass());
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Food food1 = createFood("Dog Meal", 10000, 100);
            em.persist(food1);

            Food food2 = createFood("Dog Snack", 20000, 100);
            em.persist(food2);

            OrderItem orderItem1 = OrderItem.createOrderItem(food1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(food2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = createMember("userB", "순천", "2", "2222");
            em.persist(member);

            Food food1 = createFood("Cat Meal", 4000, 150);
            em.persist(food1);

            Food food2 = createFood("Cat Snack", 8000, 50);
            em.persist(food2);

            OrderItem orderItem1 = OrderItem.createOrderItem(food1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(food2, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        private Food createFood(String name, int price, int stockQuantity) {
            Food food1 = new Food();
            food1.setName(name);
            food1.setPrice(price);
            food1.setStockQuantity(stockQuantity);
            return food1;
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }
    }

}
