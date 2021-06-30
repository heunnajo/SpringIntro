package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//memberService, memberRepository 생성해서 스프링빈에 등록하고, 후자를 전자에 연결시켜준다~!
@Configuration
public class SpringConfig {
    //이렇게 하면 스프링 데이터 JPA가 등록한 구현체가 등록된다!그리고 MemberService에 의존관계 연결하기 위해 MemberService에 매개변수로 넣어준다!
    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {//스프링 컨테이너에서 memberRepository 찾는다. 그런데 등록된 게 없다.
        //근데 등록된 게 하나 있다!
        //SpringDataMemberRepository에서 인터페이스 만들고, 스프링데이터가 제공하는 JpaRepository을 extends하면
        //스프링데이터가 인터페이스 구현체를 어떤 기술을 가지고 만들어낸다.그리고 스프링 빈에 등록한다.
        //그래서 이것을 injection으로 받을 수 있다.
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
//    @Bean
//    public MemberRepository memberRepository(){//인터페이스(MemberRepository)
        //return new MemoryMemberRepository();//구현체(MemoryMemberRepository)
        //return new JdbcMemberRepository(dataSource);//스프링 컨테이너에 등록된 dataSource를 넣어준다!
        //return new JdbcTemplateMemberRepository(dataSource);//스프링 컨테이너에 등록된 dataSource를 넣어준다!
        //return new JpaMemberRepository(em);
//    }
}
