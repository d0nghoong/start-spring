package hello_spring.hello_springTest.service;

import hello_spring.hello_springTest.repository.MemberRepository;
import hello_spring.hello_springTest.repository.MemoryMemberRepository;
import hello_spring.hello_springTest.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
    
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberrepository){

        this.memberRepository=memberrepository;
    }
    
    public Long join(Member member){
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m->{throw new IllegalStateException("이미 존재하는 회원입니다");});
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
