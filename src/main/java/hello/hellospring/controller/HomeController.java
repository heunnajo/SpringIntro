package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")//localhost:8080으로 들어올 때 첫번째 화면
    public String home(){
        return  "home";//home.html 호출~~
    }
}
