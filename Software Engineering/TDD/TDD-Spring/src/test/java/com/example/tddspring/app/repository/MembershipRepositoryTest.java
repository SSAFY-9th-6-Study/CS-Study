package com.example.tddspring.app.repository;

import com.example.tddspring.app.Dto.Membership;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

//MembershipRepository에 대한 테스트 클래스 생성
@DataJpaTest //JPA Repository들에 대한 빈들을 등록하여 단위테스트 작성에 용이하게 됨
public class MembershipRepositoryTest {

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    public void MembershipRepositoryIsNotNull(){
        assertThat(membershipRepository).isNotNull();
        //결과 MembershipRepository가 존재핮 ㅣ않아 null이 발생한다.
    }

    @Test
    public void MembershipRegisterTest(){
        // given
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipName("네이버")
                .point(10000)
                .build();

        // when
        final Membership result = membershipRepository.save(membership);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo("userId");
        assertThat(result.getMembershipName()).isEqualTo("네이버");
        assertThat(result.getPoint()).isEqualTo(10000);
    }
}
