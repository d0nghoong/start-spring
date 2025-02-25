package hello_spring.hello_springTest.controller;

import hello_spring.hello_springTest.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberservice;

    @Autowired
    public MemberController(MemberService memberservice) {
        this.memberservice = memberservice;
    }

}
