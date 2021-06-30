package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;//constructor를 이용해서 외부에서 넣어줄 수 있도록 바꾼다.

    public MemberService(MemberRepository memberRepository) {//아래처럼 외부에서 객체를 넣어주는 것을 DI(Dependency Injection)이라고 한다.
        this.memberRepository = memberRepository;
    }

    /*
    회원  가입
     */

    public Long join(Member member){
        //같은 이름의 중복 회원 X
        //cmd option V : 우변의 값을 자동완성으로 리턴 해준다.
        //Optional<Member> result = memberRepository.findByName(member.getName());//예전에는 if(null)..이렇게 했다면 현재는 Optional로 한번 감싸면 이 Optional안에 member객체가 있다.이로써 ifPresent 같은 것들을 사용할 수 있다.
        //result.get()  또는 orElseGet()를 많이 쓴다.값이 있으면 꺼내고 값이 없으면 어떤 메서드를 실행/디폴트값을 넣어서 꺼내거나 할 수 있지만 Optional을 끼고 쭈욱 쓰는 건 보기도 안 얘쁘기 때문에
        //아래와 같이 변경해준다. findByName의 리턴 타입은 Optional이므로 그냥 그 자체에 ifPresent를 쓴다.
        //이처럼 값이 나오는 것은 메서드로 구현하는 것이 좋다!
        validateDuplicateMember(member);//중복회원  검증.
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {//이미 존재한다면 아래의 로직이 동작!
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
    전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
