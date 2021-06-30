package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store = new HashMap<>();//(참고 : 현업=>공유되는변수일 경우 concurrenthashmap을 써야함)
    private static long sequence = 0L;//sequence는 0/1/2 키값 생성하주는 애. 이또한 마찬가지로 현업에서는 동시성 문제로 autumnLong으로 해줘야함.
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);//맵에 저장.
        return member;//저장된 결과 반환.
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Optional<Member> findByName(String name) {//람다를 써서 루프 돈다.filter()사용.
        //member.getName()이 파라미터로 넘어온 name과 같은지 확인.
        //findAny():하나라도 찾는 것.
        //결과가 Optional로 반환.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    @Override
    public List<Member> findAll() {//루프돌리기도 편해서 실무에서 리스트 많이 쓴다.
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
