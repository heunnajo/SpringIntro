package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스가 인터페이스 받을 땐 extends!
//인터페이스는 다중상속 가능!
public interface SpringDataMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
