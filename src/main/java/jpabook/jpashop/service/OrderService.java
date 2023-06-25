package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    /*
    * 상품 주문
    주문 내역 조회
    주문 취소

    주문 엔티티, 주문상품 엔티티 개발
    주문 리포지토리 개발
    주문 서비스 개발
    주문 검색 기능 개발
    주문 기능 테스트
    * */

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        //회원 조회
        Member member = memberRepository.findOne(memberId);

        //아이템 조회
        Item item = itemRepository.findOne(itemId);

        //배송 정보
        Delivery delivery = new Delivery();
        delivery.setDeliveryStatus(DeliveryStatus.READY);
        delivery.setMember(member);
        delivery.setAddress(member.getAddress());

        //주문 아이템
        OrderItem orderItem = OrderItem.createOderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
        return order.getId();

    }

    // 주문 취소
    @Transactional
    public void orderCancel(Long orderId) {
        Order order = orderRepository.fineOne(orderId);
        order.cancel();
    }

}
