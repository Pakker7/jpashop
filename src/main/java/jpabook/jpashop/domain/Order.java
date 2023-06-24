package jpabook.jpashop.domain;


import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Printable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

    //연관 관계 맵핑필요
    // 핵심적으로 control 하는 쪽이 set 추가해 주는 것이 좋다
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

    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

//        for(OrderItem orderItem : orderItems) {
//            order.addOrderItem(orderItem);
//        }
//        Arrays.stream(orderItems).forEach(order::addOrderItem);
        Arrays.stream(orderItems)
                .forEach(orderItem -> order.addOrderItem(orderItem));

        order.setOrdersStatus(OrdersStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;

        /* LocalDateTime의 장점
        * 불변성(Immutability): LocalDateTime은 변경 불가능한 클래스로 설계되어 있습니다. 이는 객체의 상태가 변하지 않음을 의미하며, 멀티스레드 환경에서 안전하게 사용할 수 있습니다. 반면에 java.util.Date와 java.util.Calendar는 가변 클래스로 설계되어 있어 값의 변경이 가능하고, 이로 인해 부작용이 발생할 수 있습니다.
        API 일관성: LocalDateTime은 java.time 패키지의 다른 클래스들과 일관된 API를 제공합니다. 이전의 java.util.Date와 java.util.Calendar는 API가 불안정하고 복잡하며, 사용하기 어려웠습니다. 반면에 LocalDateTime은 메서드 체인 형태로 연산을 수행할 수 있고, 다양한 유용한 메서드들을 제공하여 날짜와 시간 처리를 훨씬 쉽게 할 수 있습니다.
        시간대 독립성(Time Zone Independence): LocalDateTime은 시간대 정보를 포함하지 않습니다. 따라서 시간대 변환의 복잡성과 오류 가능성을 줄일 수 있습니다. 반면에 java.util.Date는 내부적으로 시스템 시간대에 종속적이었고, java.util.Calendar도 시간대 관련 메서드를 제공하지만 다루기 어렵고 번거로웠습니다.
        날짜 및 시간 연산 기능: LocalDateTime은 날짜와 시간을 조작하기 위한 다양한 메서드들을 제공합니다. 날짜 및 시간의 증감, 비교, 포맷팅, 파싱 등 다양한 연산을 수행할 수 있습니다. 반면에 java.util.Date와 java.util.Calendar는 연산 기능이 제한적이었고, 날짜와 시간을 다루기 위해 복잡한 로직을 작성해야 했습니다.
        JDBC와의 상호 운용성: LocalDateTime은 JDBC API와 원활하게 상호 운용할 수 있습니다. java.sql.Date와 java.sql.Time과 같은 JDBC 타입들과의 변환과 연동이 편리하게 이루어집니다.
        성능 개선: LocalDateTime은 성능 면에서도 일반적으로 더 우수합니다. 이전의 java.util.Date와 java.util.Calendar는 성능 이슈가 있었으며, 멀티스레드 환경에서 사용할 때 동기화 오버헤드가 발생할 수 있었습니다. LocalDateTime은 이러한 성능 이슈를 개선하고, 더 효율적인 방식으로 날짜와 시간을 처리합니다.
        *
        *
        * java 1.7의 Date, Calender보다 날짜계산이 훨씬 쉽고,
        * Date의 경우 가변 객체라 멀티쓰레드 환경에서 스레드간의 동기화 문제가 발생할 수 있었음. Calender는 과도한 생성 및 복사작업을 하여 성능에 부정적인 영향을 끼침
        * */
    }

    public void cancel() {
        if(delivery.getDeliveryStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("배송 완료 후에는 취소가 불가능 합니다.");
        }
        /*
        * IllegalArgumentException:
        IllegalArgumentException은 주로 메서드의 인수(argument)가 잘못된 경우 발생합니다. 즉, 메서드에 전달된 인수의 값이 허용되지 않는 범위이거나, 유효하지 않은 형식이거나, 기대하는 조건에 맞지 않는 경우에 발생합니다.
        예를 들어, 양수 값을 요구하는 메서드에 음수 값을 전달하거나, 문자열을 숫자로 변환하는 과정에서 숫자로 해석할 수 없는 형식의 문자열을 전달하는 경우 등이 IllegalArgumentException을 일으킬 수 있는 상황입니다.
        * IllegalStateException:
        IllegalStateException은 주로 객체의 상태(state)가 메서드의 호출에 부적합한 경우 발생합니다. 즉, 객체가 기대하는 상태가 아니거나, 메서드가 호출되는 시점에서 객체의 상태가 올바르지 않은 경우에 발생합니다.
        예를 들어-, 객체가 초기화되지 않은 상태에서 해당 객체의 메서드를 호출하거나, 객체가 특정 조건을 만족하지 않는 상태에서 해당 조건을 요구하는 메서드를 호출하는 경우 등이 IllegalStateException을 일으킬 수 있는 상황입니다.
        * */

        this.setOrdersStatus(OrdersStatus.CANCEL);
        this.orderItems.forEach(OrderItem::cancel); // 취소한
    }

    // 조회 로직
    public int getTotalPrice() {
        return this.orderItems.stream()
                .mapToInt(orderItem -> orderItem.getTotalPrice())
                .sum();

    }
}
