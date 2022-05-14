package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

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
}
