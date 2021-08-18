package springjpa2.repository;

import lombok.Getter;
import lombok.Setter;
import springjpa2.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

}
