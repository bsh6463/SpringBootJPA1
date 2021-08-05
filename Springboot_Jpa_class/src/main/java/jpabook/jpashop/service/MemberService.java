package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//@Transactional //JPA의 모든 데이터 변경 및 로직은 transaction에서 실행되어야 함. 클래스에 지정하면 메서드마다 걸림.
@Service
@Transactional(readOnly = true)//JPA가 조회하는 메서드에 좀더 최적화됨.읽기에는 가급적 readOnly 적용.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        //중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member); //Persist시점에 id 존재 보장됨. id가PK기 때문에 DB에 저장되기 위해서 필요함.
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다. ㅜㅜ");
        }
    }

    /**
     * 전체 회원 조회
     */
    //@Transactional(readOnly = true) //JPA가 조회하는 메서드에 좀더 최적화됨.
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 한 명 조회 by id
     */
    //@Transactional(readOnly = true) //읽기에는 가급적 readOnly 적용.
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

}
