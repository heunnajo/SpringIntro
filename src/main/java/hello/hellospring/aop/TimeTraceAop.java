package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect//이 애노테이션이 있어야 aop를 쓸 수 있다!
@Component
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))")//패키지 명과 클래스명, 파라미터, 등등 원하는 정보 넣을 수 있다. hello.hellospring하위에 모두  적용!
    //@Around("execution(* hello.hellospring.service..*(..))")//service 하위의 것들만 한다!
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());//무슨 메서드를 호출하는지 다 얻을 수 있다.
        try{
            return joinPoint.proceed();//다음 메서드로 진행.
        } finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString()+" " + timeMs+"ms");
        }

    }
}
