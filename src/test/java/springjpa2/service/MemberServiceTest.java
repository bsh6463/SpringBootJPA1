package springjpa2.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springjpa2.domain.Member;
import springjpa2.repository.MemberRepository;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void  회원가입() throws Exception{
        //given
        Member member= new Member();
        member.setName("kim");
        //when
        Long savedId = memberService.join(member);

        //then
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(savedId));
    }


    @Test(expected = IllegalStateException.class)
    public void  중복_회원_예회() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");


        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        org.junit.jupiter.api.Assertions.fail();

    }

}