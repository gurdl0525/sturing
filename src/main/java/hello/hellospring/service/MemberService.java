package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service// 서비스가 없을때는
// 스프링이 순수한 자바 클래스를 와이드 해줄 수 없기 때문에
// 스프링이 알아 들을수 있게 어노테이션을 걸어준다
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        // @BeforeEach에서 만든 MemeberService를 여기에 넣어줌
        this.memberRepository = memberRepository;
        // 따라서 같은 리포지토리를 사용하게됨
        // 이러한 방식을 DI라고 한다
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        long start = System.currentTimeMillis();

        try{
            validateDuplicateMember(member);
            // 원래 중복회원 검증 로직이 있던 자리, 현재는 메소드
            memberRepository.save(member);
            // 회원 가입을 하면
            return member.getId();
            // 아이디만 반환해 주겠다.
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복회원 불가
        // 중복회원 검증
        memberRepository.findByName(member.getName())
                // memberRepository에서 이름 찾아 검색
                // 그 결과는 Optional형태의 member니까 null이 와도 처리가능
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
                // = meber.ifPresent 와 같다
                // ifPresent = 만약값이 null이 아니면 아래로직 동작

                // 이미 있는 이름이라면 아직 name이 저장되기전에 같은 이름이 있음으로
                // null값이 될 수 없음, 따라서 null값이 아니라면 같은 이름이 있음으로 중복회원
                // 그러한 이유로 ifPresent를 통과해서 IllegalStateException("이미 존재하는 회원입니다.")을 Throw

        // 위 로직을 메소드로 만들어줌
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        long start =System.currentTimeMillis();
        try{
            return memberRepository.findAll();
            // memberRepository의 list들을 전부 찾아 반환
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
        // memberId를 찾아 반환
    }
}
