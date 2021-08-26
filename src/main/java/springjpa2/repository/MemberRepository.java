package springjpa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springjpa2.domain.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //select m from Member m where m.name = ?
    List<Member> findByName(String name); //와 여기서 끝임 쿼리 만들어줌


}
