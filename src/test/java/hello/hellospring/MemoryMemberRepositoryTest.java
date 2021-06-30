package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    //MemberRepository repository = new MemoryMemberRepository();
    //MemoryMemberRepository만 테스트하는 거니까 아래처럼 인터페이스가 아니라 바꾼다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach//메서드 실행이 끝날 때마다 동작한다. save() 끝나고 afterEach(), findByName() 끝나고 afterEach(), findAll() 끝나고 afterEach()
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = "+ (result == member));
        //Assertions.assertEquals(member,null);//(expected,actual)
        assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");;
        repository.save(member2);

        Member result = repository.findByName("spring1").get();//get()을 하면 optional을 한번 깔 수 있다.
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
