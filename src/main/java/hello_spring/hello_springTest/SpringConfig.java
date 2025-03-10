package hello_spring.hello_springTest;

import hello_spring.hello_springTest.repository.MemberRepository;
import hello_spring.hello_springTest.repository.MemoryMemberRepository;
import hello_spring.hello_springTest.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}
