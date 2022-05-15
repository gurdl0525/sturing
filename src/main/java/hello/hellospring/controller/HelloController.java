package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //Thymleaf
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","spring!!");
        // = model(data:hello!!!) 모델에 data는 spring!!라고 넣어놓음
        // 여기서 data는 attributeName, 즉 키를 의미
        // spring!!은 attributeValue, 즉 값을 의미

        return "hello";
        // return "hello"는 hello.html을 찾아 가서 렌더링하라
        // = hello.html을 출력하라.
        // ※return은 templates아래에 있는 파일을 찾아감※

        // 컨트롤러가 리턴값으로 문자를 변환하면 뷰 리졸버(viewResolver)가 화면을 찾아 처리

        // 스프링 부트 템플릿엔진 기본 viewName매핑은 다음과 같다
        // resources:templates/ + {viewName} + .html
        // 따라서 viewName이 hello로 바뀌기 때문에 hello.html로 간다.
    }

    //mvc,템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // @RequestParam("name") = name이란 매개변수 생성 및 name값을 받아라~
        // String name, Model model 문자열형의 name변수 생성(위 name과는 다름) 및 모델 생성
        model.addAttribute("name", name);
        // 매개변수 name에 문자열 변수 name값을 넣어라
        return "hello-template";
        // template아래에서 hello-template.html에 모델값과 반환값 전달
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name")String name){
        return "hello " + name;
    }

    //API
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        //객체 hello생성
        hello.setName(name);
        //hello에 setName추가
        return hello;
        //객체를 리턴(JSON)방식
    }

    //java 빈 jack
    //프로퍼티 접근 방식
    static class Hello{
        private String name;
        // 키가 프라이빗 이기 때문에 바깥에서 사용할 수 가 없다
        public String getName() {
            return name;
            //꺼낼때는 getName
        }
        //그렇기 때문에 메소드를 이용해서 사용
        public void setName(String name) {
            this.name = name;
            //넣을때는 setName
        }
    }
}
