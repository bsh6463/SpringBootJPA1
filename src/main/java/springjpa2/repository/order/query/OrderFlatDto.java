package springjpa2.repository.order.query;

import lombok.Data;
import lombok.Getter;
import springjpa2.domain.Address;
import springjpa2.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
public class OrderFlatDto {

    //order
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    //orderItem
    private String itemName;
    private int orderPrice;
    private int count;


    public OrderFlatDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}