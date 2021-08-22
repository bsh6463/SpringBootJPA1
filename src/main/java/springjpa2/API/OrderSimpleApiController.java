package springjpa2.API;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springjpa2.domain.Address;
import springjpa2.domain.Order;
import springjpa2.domain.OrderStatus;
import springjpa2.repository.OrderRepository;
import springjpa2.repository.OrderSearch;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/api/v2/simple-orders")
    public List<SimPleOrderDto> odersV2(){

        //ORDER 2개 조회됨.
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimPleOrderDto> result = orders.stream()
                .map(o -> new SimPleOrderDto(o)) //map은 a를 b로 바꾸는 것.
                .collect(Collectors.toList());

        return result;
    }

    @Data
    private class SimPleOrderDto {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimPleOrderDto(Order order){
            this.orderId = order.getId();
            this.name = order.getMember().getName();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
        }
    }


    @GetMapping("/api/v3/simple-orders")
    public List<SimPleOrderDto> ordersV3(){

      List<Order> orders = orderRepository.findAllWithMemberDelivery();

      List<SimPleOrderDto> result = orders.stream()
              .map(o -> new SimPleOrderDto(o))
              .collect(Collectors.toList());

      return result;
    }




}
