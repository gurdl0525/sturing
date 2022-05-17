package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach// 각 Test 실행하기전에 ~~
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        // MemoryMemberRepository를 생성
        // @BeforeEach에서 만든 MomoryMemberRepository를 memberRepository에다가 넣는다
        memberService = new MemberService(memberRepository);
        // 즉 test를 실행할 때마다 각각 MemoryMemberRepository와 MemeberService를 생성해줌
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
        // Test끝날때마다 clearStroe를 호출
    }

    // given = 주어진것
    // when = 이것을 실행했을때
    // than = 결과가 이게 나와야 해
    @Test
    //정상 플로
    void 회원_가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        // memebr의 이름 셋팅

        //when
        Long saveId = memberService.join(member);
        // member의 join을 검증할 코드
        // join에서 id만 반환하기로 정했기 때문에
        // saveId, 아이디 저장장

        //than
        // 검증코드
        Member findMember = memberService.findOne(saveId).get();
        // 반환된 savId값에 해당하는 repository반환 및 findMember에 저장
        assertThat(member.getName()).isEqualTo(findMember.getName());
        // 스태틱 임포트
        // member의 저장 되어있는 이름이  findMember(레포지토리)에 저장 되어있는 이름과 같은지 확인
    }

    @Test
    //에외 플로
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        /*try{
            memberService.join(member2);
            // 위 코드가 실행되고
            fail();
            // Exception터지지 않으면 실패(아래쥴 코드가 실행되면)
        }catch (IllegalStateException e){
            // catch(IllegalStateException e) = Exception이 터지면 아래 코드실행
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            // e에서 얻은 메세지, 즉 join에서 예외코드에서 던진 메세지와
            // "이미 존재하는 회원입니다."가 같은지 확인하는 코드
        }*/ // 하지만 이것을 위해서 try catch를 쓰기는 애매함
       IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
        // memberService.join(member2)로직을 태울때
        // IllegalStateException이 터져야(작동해야) 한다.

        //than
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // 위 코드(join)에서 받은 메세지가 "이미 존재하는 회원입니다."와 같아야 한다.
    }
}