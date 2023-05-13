package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @DisplayName("")
    @Rollback(false) // rollback이 자동으로 되는데 false 시킴
    public void testMember() throws Exception{
        // given
        Member member = new Member();
        member.setUserName("member");

        // when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        Assertions.assertThat(findMember).isEqualTo(member); // 같은 트랜젝션 영속성 컨텍스트에 있으니까 .. 같게 나오는거임
        System.out.println("is equal?? : " + (member == findMember ? "YES" : "NO"));
    }

    @Test
    public void save() {
    }

    @Test
    public void find() {
    }
}