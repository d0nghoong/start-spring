package hello_spring.hello_springTest.service;

import hello_spring.hello_springTest.domain.Member;
import hello_spring.hello_springTest.repository.MemberRepository;
import hello_spring.hello_springTest.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.standard.expression.Each;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberservice;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void setUp(){
        memberRepository=new MemoryMemberRepository();
        memberservice=new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() throws Exception {
        //given
        Member member=new Member();
        member.setName("hello");

        //when
        Long joinId = memberservice.join(member);

        //then
        Member findMember = memberservice.findOne(joinId).get();
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복_회원() throws Exception {
        Member member=new Member();
        member.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

         memberservice.join(member);

        /* try{
             memberservice.join(member2);
             fail();
         }catch(Exception e){
             assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

        }*/

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberservice.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }
}