package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) //Order table의 어느 컬럼에서 읽어옴???
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //ORDINAL 은 0 1 2 3 숫자로 들어감. 중간에 다른 상태 추가되면 난리남. 숫자 매칭이 달라짐.
    private DeliveryStatus status; //READY, COMP

}
