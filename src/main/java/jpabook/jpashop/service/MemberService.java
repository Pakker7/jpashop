package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class MemberService {

    private MemberRepository memberRepository;


    /*
    * 회원가입
    * */
    @Transactional // 변경
    public void join(Member member) {
        validationDuplicateMember(member);
    }

    private void validationDuplicateMember(Member member)  {
        List<Member> findMembers = memberRepository.findName(member.getUserName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            //throw new Exception();
            // 그냥 Exception은 함수 위에 throw excepiton 추가 해야되는데 이거는 안해도 됨
        }
    }


    /*
    * 회원 목록 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
    * 회원 조회
    * */
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

}
