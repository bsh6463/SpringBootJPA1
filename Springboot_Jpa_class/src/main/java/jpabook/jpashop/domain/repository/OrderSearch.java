package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName; //회원 이름으로 주문 검색
    private OrderStatus orderStatus; //주문 상태로 주문 검색
}
