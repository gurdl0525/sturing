package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 저장 공간
    private static Map<Long, Member> store = new HashMap<>();
    // 동시성 문제가 고려되어있지 않아, ConcurrentHashMap사용 고려
    private static long sequence = 0L;
    // sequence는 키값을 생성해준다.
    // 동시성 문제가 고려되어있지 않아, long대신 AtomicLong사용 고려


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        // store로 가기전에 member의 id값 셋팅
        // member의 name은 이미 저장된 상태
        store.put(member.getId(), member);
        // store 저장
        return member;
        // 저장된 값 반환
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // 과거에는 return store.get(id);를 썼으나
        // 값이 null값일 경우를 대비해 Optional로 감싸서 반환
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                // member -> member.gerName().equals(name)
                // = member.getName이 파라미터로 넘어온 name과 같은지 확인
                // 같은 경우에만 필터링이 됨
                .findAny();
                // 그중에서 찾으면 반환
                // 끝까지 돌렸는데도 없으면 null이 Optional을 통해서 반환됨
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store의 있는 values가 member 때문에
        // 결국에는 member를 List로 반환
    }
    public void clearStore() {
        store.clear();
        // 스토어를 비우는 코드 작성
    }
}
