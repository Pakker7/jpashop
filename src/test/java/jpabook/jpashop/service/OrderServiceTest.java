package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrdersStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class) // junit4랑 같이 테스트 할 때 추가
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Autowired ItemRepository itemRepository;

    @Test
    @DisplayName("")
    public void 상품주문() throws Exception{
        // given
        Member member = createMember();
        Book book = createBook();

        // when
        int count = 5;
        Long orderId = orderService.order(member.getId(), book.getId(), count);

        // then
        Order order = orderRepository.fineOne(orderId);
        Book bookResult = (Book) itemRepository.findOne(book.getId());

        assertEquals("상품 주문시 상태는 ORDER", OrdersStatus.ORDER, order.getOrdersStatus());
        assertEquals("상품 주문시 총 가격은 5000원", book.getPrice()*count , order.getTotalPrice());
        assertEquals("상품 주문 후 상품 갯수는 5이다.", 5, bookResult.getStockQuantity());
        //객체비교 되는지 확인
        assertEquals("상품 주문시 배송정보는 멤버 주소와 같다.", member.getAddress().hashCode(), order.getDelivery().getAddress().hashCode());
    }

    @Test(expected = NotEnoughStockException.class) // 예상 Exception
    @DisplayName("")
    public void 상품주문_수량초과() throws Exception{
        // given
        Member member = createMember();
        Book book = createBook();

        // when
        int count = 20;
        orderService.order(member.getId(), book.getId(), count);

        // then



    }

    @Test
    @DisplayName("")
    public void 주문내역조회() throws Exception{
        // given

        // when

        // then
    }

    @Test
    @DisplayName("")
    public void 주문취소_아이템갯수롤백() throws Exception{
        // given

        // when

        // then
    }

    @Test
    @DisplayName("")
    public void 주문취소_배송중_취소불가() throws Exception{
        // given

        // when

        // then
    }

    private Member createMember() {
        Member member = new Member();
        member.setAddress(new Address("서울","허준로","182736"));
        member.setUserName("강호동");
        member.setMobile("01000000000");
        em.persist(member);

        return member;
    }

    private Book createBook(){
        Book book = new Book() ;
        book.setItemName("책 이름");
        book.setPrice(1000);
        book.setStockQuantity(10);
        book.setAuther("거북이");
        em.persist(book);
        return book;
    }
}
