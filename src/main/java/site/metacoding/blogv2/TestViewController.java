package site.metacoding.blogv2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestViewController {
    
    @GetMapping("/1")
    public String Test1(){
        return "post/detail";
    }
    @GetMapping("/2")
    public String Test2(){
        return "post/list";
    }
    @GetMapping("/3")
    public String Test3(){
        return "post/updateForm";
    }@GetMapping("/4")
    public String Test4(){
        return "post/writeForm";
    }@GetMapping("/5")
    public String Test5(){
        return "user/detail";
    }@GetMapping("/6")
    public String Test6(){
        return "user/joinForm";
    }@GetMapping("/7")
    public String Test7(){
        return "user/loginForm";
    }
    @GetMapping("/8")
    public String Test8(){
        return "user/updateForm";
    }
}
