package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrdersStatus ordersStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // one to one 에서는 아무거나 메인으로 잡아도 되는데, 이번에는 메인을 order로 잡겠음.
    // 그럼 Delivery에서는 mappyedby를 delivery로 적어야 겠지?
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //모든 연관관계는 지연로딩으로 설정 @OneToOne, @ManyToOne 둘다 lazy
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // mappedBy = order 테이블에 있는 member 필드로 맵핑된거야 라는 뜻
    // 맵드베..를 적는 순간 나는 내가 맵핑을 하는 애가 아니고, member에 의해서 맵핑된 거울일 뿐이야 = 읽기 전용
    // 그래서 실제 이 Member의 orders에 값을 넣는다고 해서 실제 Order Entity의 FK값이 변경되지 않음
    // 반대로 Order Entity에서  member의 member_id값을 바꾸면 다른 member로 FK값이 변경이 됨~~~~
    // 영향 주는 얘는 실제 FK 맵핑하는 값들임
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate; // 주문 시간

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }


}
