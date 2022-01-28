package sorzzzzy.dearmydog.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sorzzzzy.dearmydog.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    // 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 리스트 조회
    public List<Member> findAll() {
        // 첫번째 인자 : JPQL, 두번째 인자 : 반환타입
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 이름으로 조회
    public List<Member> findByName(String name) {
        // 첫번째 인자 : JPQL, 두번째 인자 : 반환타입
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
