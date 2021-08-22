package springjpa2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springjpa2.domain.*;
import springjpa2.domain.item.Book;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * userA
 *  JPA1 BOOK
 *  JPA2 BOOK
 * userB
 *  JPA1 BOOK
 *  JPA2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct //스프링 빈 생성 후 실행됨.
    public void init(){
        initService.doInit();
        initService.doInit2();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{


        private final EntityManager em;
        public void doInit(){
            Member member = createMember("userA", "서울","송파", "232");
            em.persist(member);


            Book book1 = createBook("JPA1 BOOK", 10000,100 );

            Book book2 = createBook("JPA2 BOOK", 20000,100);

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);


            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);



        }



        public void doInit2(){
            Member member = createMember("userB", "부산", "강남남", "222");
            em.persist(member);


            Book book1 = createBook("spring1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("spring2 BOOK", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 4);

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

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }


    }

}


