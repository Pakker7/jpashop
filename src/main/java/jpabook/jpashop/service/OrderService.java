package jpabook.jpashop.service;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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

    public void order(Order order) {
        //회원 조회


    }


}
