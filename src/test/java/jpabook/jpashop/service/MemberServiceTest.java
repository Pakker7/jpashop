package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit4랑 같이 테스트 할 때 추가
@SpringBootTest
@Transactional // @Transactional이 Test 코드에 있으면 기본적으로 rollback을 함.
               // jpa에서는 코드에서 save할 때 실제로 db에 insert되는게 아니고, db commit을 할 때 insert되니까. insert 하기 전에 @Transactional에서 rollback 해버림 그래서 DB에 저장이 안됨
               // 그래서 insert 쿼리도 안나감.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(false) // 그냥 여기에 넣어주면 직관적으로 사람이 이해 할 수 있음
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setUserName("Kim");

        //when
        Long saveId = memberService.join(member);

        //then
        em.flush(); // 이거를 넣어주면 insert 쿼리가 들어감. 그럼 insert된 후에 @Transactional이 rollback해줌
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setUserName("kim");
        Member member2 = new Member();
        member2.setUserName("kim");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2); // exception 터질 거임
        } catch (IllegalStateException e){ // 같은 exception이면 catch를 타면서 정상 작동
            return ;
        }


        //then
        //예외 발생이 안되고 로직이 밖으로 나가버리면 잘못되는 거임
        fail("여기로 오면 안됨.. 예외가 발생해야 한다....");
    }


}