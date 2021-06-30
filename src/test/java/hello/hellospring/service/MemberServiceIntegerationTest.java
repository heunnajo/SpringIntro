package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegerationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;//또 다른 객체를 생성하게 되면 다른 인스턴스기 때문에 내용이 달라질 수 있다!

    @Test
    //@Commit //DB에 저장!
    void 회원가입() {//추천하는 문법 : given,when,then 문법
        //given : 어떤 상황에서 ; 이 데이터를 기반으로 하는구나!
        Member member = new Member();
        member.setName("helloHeunna");

        //when : 이걸 실행했을 때 ; 이걸 검증하는구나!
        Long saveId = memberService.join(member);

        //then : 아래의 결과가 나와야함.;확인할 부분이구나!
        //Optional<Member> one = memberService.findOne(saveId);
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);//회원을 spring으로 만들면 첫번째 join에서 터지게 된다!=>마찬가지로 데이터를 clear해줘야한다!
        //콤마 뒤의 로직을 실행할 때 예외상항이라면 콤마 앞(IllegalStateExcption)이 니와야한다!
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//람다로 예외 검출!

        //메시지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}