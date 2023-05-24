package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String userName;

    @Embedded
    private Address address;

    private String mobile;

    // mappedBy = order 테이블에 있는 member 필드로 맵핑된거야 라는 뜻
    // 맵드베..를 적는 순간 나는 내가 맵핑을 하는 애가 아니고, member에 의해서 맵핑된 거울일 뿐이야 = 읽기 전용
    // 그래서 실제 이 Member의 orders에 값을 넣는다고 해서 실제 Order Entity의 FK값이 변경되지 않음
    // 반대로 Order Entity에서  member의 member_id값을 바꾸면 다른 member로 FK값이 변경이 됨~~~~
    // 영향 주는 얘는 실제 FK 맵핑하는 값들임
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    // 초기화 필수
    // 1. null문제에서 안전해짐
    // 2. 하이버네이트는 엔티티를 영속화 할 때, 컬랙션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로
    //변경한다. 만약 getOrders() 처럼 임의의 메서드에서 컬력션을 잘못 생성하면 하이버네이트 내부
    //메커니즘에 문제가 발생할 수 있다. 따라서 필드레벨에서 생성하는 것이 가장 안전하고, 코드도 간결하다

}
