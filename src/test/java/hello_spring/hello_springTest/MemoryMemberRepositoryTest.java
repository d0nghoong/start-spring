package hello_spring.hello_springTest;

import hello_spring.hello_springTest.domain.Member;
import hello_spring.hello_springTest.repository.MemoryMemberRepository;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repos=new MemoryMemberRepository();

    @AfterEach
    public void clear(){
        repos.clearStore();
    }
    @Test
    public void save(){

        Member member1=new Member();
        member1.setName("spring1");
        repos.save(member1);

        Member result=repos.findById(member1.getId()).get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("spring1");
        repos.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repos.save(member2);

        Member result=repos.findByName("spring2").get();
        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repos.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repos.save(member2);

        List<Member> result= repos.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
