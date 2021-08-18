package springjpa2.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springjpa2.domain.Address;
import springjpa2.domain.Member;
import springjpa2.domain.Order;
import springjpa2.domain.OrderStatus;
import springjpa2.domain.item.Book;
import springjpa2.domain.item.Item;
import springjpa2.exception.NotEnoughStockException;
import springjpa2.repository.OrderRepository;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    @Test
    public void 상품주문() throws Exception{
        Member member = createMember();

        Item book = createBook("ㄴㅇㄹㄴㅇㄹㄴㅇㄹ", 10000, 10 );

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        Assert.assertEquals("주문한 상품 종류 수가 같아야 한다.", 1, getOrder.getOrderItems().size());
        assertThat(getOrder.getTotalPrice()).isEqualTo(10000*orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(10-orderCount);
    }

    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "dff", "321321"));
        em.persist(member);
        return member;
    }

    @Test
    public void 주문취소() throws Exception{
        Member member = createMember();
        Item book = createBook("dddd", 10000,10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        assertThat(book.getStockQuantity()).isEqualTo(10);
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);


    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        Member member = createMember();
        Item book = createBook("ddd", 10000, 30);

        int orderCount = 50;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        fail("재고수량 부족 예외가 터져야함.");
    }

}