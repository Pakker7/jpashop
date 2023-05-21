package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Delivery {


    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery") // Order에도 name을 delivery라고 쓰고, 여기도 delivery라고 쓰냐?
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;


}
