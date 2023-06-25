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

    // one to one 에서는 아무거나 메인으로 잡아도 되는데, 이번에는 order로 잡겠음.
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // Enum에는 꼭 넣어야 한다.
    // EnumType.ORDINAL -> default임 0,1 이런식으로 숫자로만 나옴.... default가 그러니까..절대 조심하고 꼭 EnumType.STRING 넣어야 함
    private DeliveryStatus deliveryStatus;


}
