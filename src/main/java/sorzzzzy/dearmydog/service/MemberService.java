package sorzzzzy.dearmydog.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorzzzzy.dearmydog.domain.Member;
import sorzzzzy.dearmydog.repository.MemberRepository;

import java.util.List;

@Service
// 'readOnly = true' 옵션은 읽기 전용 트랜잭션에서 성능을 최적화 시켜줌
@Transactional(readOnly = true)
// final이 있는 필드의 생성자 자동 생성
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional // 데이터가 변경되어야 하는 부분에는 따로 어노테이션을 붙임
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        // 문제가 생기면 예외 발생
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    // 회원 수정
    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
