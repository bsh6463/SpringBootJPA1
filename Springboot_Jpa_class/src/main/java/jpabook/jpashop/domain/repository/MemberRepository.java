package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //스프링 빈으로 등록, 컴포넌트 스캔.
@RequiredArgsConstructor
public class MemberRepository {

    // JPA 표준 annotaion
    //스프링이 entitymanager 만들어저 주입해줌.
    //lombok으로도 쌉가능. 스프링DataJPA가 @Autowired로 em 주입 가능하도록 지원함.
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        //JPQL 사용 createQuery(JPQL, 반환타입)
       return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) //:name이랑 binding됨.
                .getResultList();
    }

}
