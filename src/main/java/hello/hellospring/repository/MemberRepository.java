package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {//4가지 기능 구현:1.저장 2. id로 찾기 3.name으로 찾기 4.저장된 모든 회원 리스트 반환
    Member save(Member member);//
    Optional<Member> findById(Long id);//Java8 : Optional이란 객체가 Null일 수도 있는데 null을 반환하는 방법 중에 Optional로 감싸서 반환하는 방법.
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
