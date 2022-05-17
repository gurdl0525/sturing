package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
     Member save(Member member);// 회원 저장 기능
     Optional<Member> findById(Long id);// Id를 찾아 오는 기능
     Optional<Member> findByName(String name);// Name을 찾아 오는 기능
     // findByName이나 findById가 null일경우가 있다
     // null을 그대로 반환하는 것 보다 Optional로 감싸서 반환하는 경우를 선호
     List<Member> findAll();
     // List를 다 반환 해주는 기능
}
