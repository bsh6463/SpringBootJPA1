package springjpa2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;


    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Order> orderList = new ArrayList<>();

}
