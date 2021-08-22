package springjpa2.API;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springjpa2.domain.Order;
import springjpa2.repository.OrderRepository;
import springjpa2.repository.OrderSearch;

import java.util.List;

/**
 * 연관관계(x to One)
 * Order-> Member (N:1)
 * Order -> Delivery (1:1)
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order>ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        for (Order order : all) {
            order.getMember().getName(); //프록시 강제 초기화.
            order.getDelivery();
        }
        return all;
    }
}
