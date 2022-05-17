package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    //디펜던시 인젝션
    @Autowired// 연결 시켜 주는 어노테이션
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}