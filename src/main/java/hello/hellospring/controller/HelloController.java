package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //웹 애플리케이션에서 /hello가 들어오면 이 메서드를 호출해준다!!!!
    @GetMapping("hello")
    public String hello(Model model){//스프링이 Model이라는 것을 만들어서 넣어준다.
        model.addAttribute("data","흔나 화이팅!!!");//data로 hello를 넘긴다.
        return "hello";//현재 컨트롤러. 템플릿에서 hello.html를 찾는다. 찾아서 렌더링한다.
    }
    @GetMapping("hello-mvc")
    //url을 파라미터로 바꿔본다. Model을 매개변수로 담으면 뷰에서 렌더링할 때 쓴다!
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";//hello-template으로 간다!
    }
    @GetMapping("hello-string")
    @ResponseBody//중요!http에서 header,body 중 body부분에 데이터를 내가 직접 넣겠다는 의미.
    //뷰를 쓰지 않고 직접.
    public String helloString(@RequestParam("name") String name){//String name 값이 SPRING이면
        return "hello" + name;//hello SPRING
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;//문자가 아닌 객체를 넘긴다!!!기본적으로 json으로 반환한다!
    }
    //Hello라는 객체 먼저 생성!
    public class Hello{
        private String name;//private이라서 외부에서 바로 못꺼낸다! 아래의 get, set 메서드를 통해 접근하게 된다!
        //property 접근방식.
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
