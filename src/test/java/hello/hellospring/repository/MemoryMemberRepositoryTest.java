package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
        // 테스트 끝날때 마다 리포지토리를 한번씩 지워주는 코드
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        // member의 name을 spring으로 값을 넣어줌
        repository.save(member);
        // 리포지토리에 member값을 저장 해줌
        // 저장할때 MemoryMemberRepository에서 id 값을 셋팅 해주기로 헀기 때문에
        Member result = repository.findById(member.getId()).get();
        // id를 가지고 오는 확인
        // .get()은 반환타입이 Optional일 때 써준다.
        // 검증하기 위해서 위 new에서 한거랑 DB에서 뽑은거랑 같으면 참이된다.

        /**
         *Assertions.assertEquals(member, result);
         * result랑 member랑 같은지 확인
         */
        //엿으나 요즘은 아래 형태를 더 많이 씀
        assertThat(member).isEqualTo(result);// Assertions를 우클릭한후 스태틱 임포트 해주기
        // member가 result랑 똑같다는걸 검증
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        // member1에 name값 셋팅
        repository.save(member1);
        // member1값 저장

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        // findByName으로 spring1을 찾아서 get으로 꺼냄
        // result에 spring1을 저장
        assertThat(result).isEqualTo(member1);
        // member1의 값이 result 즉 spring1과 같은지 검증
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        // 이름값 셋팅 및 member1저장

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        // 이름값 셋팅 및 member2저장

        List<Member> result = repository.findAll();
        // Member리포지토리의 모든값을 검색 및 result에 저장
        assertThat(result.size()).isEqualTo(2);
        // member1과 member2두가지만 있기때문에 result의 크기는 2
        // 따라서 result의 size가 2인인지 확인
        // 하지만 잘작동하던 findByName이 오류가 생긴다.
    }
    // 이는 findAll()에서 member1, member2객체에 이미 값이 저장되어 버려서
    // findByName()에서 오류가 생긴다. 따라서 Test가 끝날때마다 데이터를 클리어 해줘야한다.
}
// 방금은 개발을 하고 test를 했지만
// test를 먼저 해보고 이에 맞춰서 클래스를 작성하는 방법이있다.
// 그방법을 Tdd(테스트 주도개발)이라고 한다.